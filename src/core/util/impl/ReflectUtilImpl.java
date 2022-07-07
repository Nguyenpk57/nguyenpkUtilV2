package core.util.impl;

import core.module.ModuleFactory;
import core.util.CollectionUtil;
import core.util.ReflectUtil;
import core.util.StringUtil;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;

public class ReflectUtilImpl implements ReflectUtil {

    private final CollectionUtil collectionUtil = ModuleFactory.getFactory().collectionUtil();
    private final StringUtil stringUtil = ModuleFactory.getFactory().stringUtil();
    private static final String GETTER_PREFIX = "get";
    private static final String GETTER_BOOLEAN_PREFIX = "is";
    private static final String SETTER_PREFIX = "set";

    @Override
    public String getStack() {
        return getStack(new Throwable());
    }

    @Override
    public String getStack(Throwable e) {
        if (e == null) {
            e = new Throwable();
        }
        StackTraceElement[] es = e.getStackTrace();
        if (es == null) {
            return null;
        }
        int n = es.length;
        if (n <= 1) {
            return null;
        }
        StackTraceElement se = es[1];
        return se.getClassName() + "." + se.getMethodName() + ":" + se.getLineNumber();
    }

    @Override
    public List<Class> getSuperclasses(Class cl) {
        List<Class> l = collectionUtil.emptyList();
        Class sc = cl.getSuperclass();
        while (sc != null && !Object.class.equals(sc)) {
            l.add(sc);
            sc = sc.getSuperclass();
        }
        if (Object.class.equals(sc)) {
            l.add(sc);
        }
        return l;
    }

    @Override
    public Class getLastSuperclass(Class cl) {
        Class lsc = null;
        if (cl == null) {
            return lsc;
        }
        String className = cl.getName();
        List<Class> l = collectionUtil.emptyList();
        l.addAll(getSuperclasses(cl));
        if (l.contains(Object.class)) {
            l.remove(Object.class);
        }
        lsc = l.isEmpty() ? null : l.get(l.size() - 1);
        return lsc;
    }

    @Override
    public List<Method> getMethods(Class clazz) {
        if (clazz == null) {
            return collectionUtil.emptyList();
        }
        List<Method> methods = collectionUtil.toList(clazz.getMethods());
        return methods;
    }

    @Override
    public List<Method> getGetters(Class clazz) {
        if (clazz == null) {
            return collectionUtil.emptyList();
        }
        List<Method> getters = collectionUtil.emptyList();
        List<Method> methods = getMethods(clazz);
        for (Method method : methods) {
            if (isGetter(method)) {
                getters.add(method);
            }
        }
        return getters;
    }

    private boolean isGetter(Method method) {
        if (method == null) {
            return false;
        }
        String methodName = method.getName();
        return (Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 0)
                && ((!Void.TYPE.equals(method.getReturnType()) && methodName.startsWith(GETTER_PREFIX)) || (Boolean.TYPE.equals(method.getReturnType()) && methodName.startsWith(GETTER_BOOLEAN_PREFIX)));
    }

    @Override
    public List<Method> getSetters(Class clazz) {
        if (clazz == null) {
            return collectionUtil.emptyList();
        }
        List<Method> setters = collectionUtil.emptyList();
        List<Method> methods = getMethods(clazz);
        for (Method method : methods) {
            if (isSetter(method)) {
                setters.add(method);
            }
        }
        return setters;
    }

    private boolean isSetter(Method method) {
        if (method == null) {
            return false;
        }
        String methodName = method.getName();
        return Modifier.isPublic(method.getModifiers()) && method.getParameterTypes().length == 1
                && Void.TYPE.equals(method.getReturnType()) && methodName.startsWith(SETTER_PREFIX);
    }

    @Override
    public Method getMethod(Class clazz, String methodName) {
        methodName = stringUtil.trim(methodName);
        if (clazz == null || stringUtil.isEmpty(methodName)) {
            return null;
        }
        Method method = null;
        List<Method> methods = getMethods(clazz);
        for (Method m : methods) {
            if (stringUtil.equals(methodName, m.getName())) {
                method = m;
                break;
            }
        }
        return method;
    }

    @Override
    public Method getGetter(Class clazz, Field field) {
        if (clazz == null || field == null) {
            return null;
        }
        String fieldName = field.getName();
        Method getter = null;
        List<Method> getters = getGetters(clazz);
        for (Method g : getters) {
            if (isFieldGetter(g.getName(), fieldName) && field.getType().equals(g.getReturnType())) {
                getter = g;
                break;
            }
        }
        return getter;
    }

    private boolean isFieldGetter(String getterName, String fieldName) {
        getterName = stringUtil.nvl(getterName);
        fieldName = stringUtil.nvl(fieldName);
        return getterName.equals(GETTER_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1)) || getterName.equals(GETTER_BOOLEAN_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
    }

    @Override
    public Method getSetter(Class clazz, Field field) {
        if (clazz == null || field == null) {
            return null;
        }
        String fieldName = field.getName();
        Method setter = null;
        List<Method> setters = getSetters(clazz);
        for (Method s : setters) {
            if (isFieldSetter(s.getName(), fieldName) && field.getType().equals(s.getParameterTypes()[0])) {
                setter = s;
                break;
            }
        }
        return setter;
    }

    private boolean isFieldSetter(String setterName, String fieldName) {
        setterName = stringUtil.nvl(setterName);
        fieldName = stringUtil.nvl(fieldName);
        return setterName.equals(SETTER_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
    }

    @Override
    public List<Field> getFields(Class clazz) {
        if (clazz == null) {
            return collectionUtil.emptyList();
        }
        List<Field> fields = collectionUtil.toList(clazz.getDeclaredFields());
        return fields;
    }

    @Override
    public Field getField(Class clazz, String fieldName) {
        fieldName = stringUtil.trim(fieldName);
        if (clazz == null || stringUtil.isEmpty(fieldName)) {
            return null;
        }
        Field result = null;

        List<Field> fields = getFields(clazz);
        for (Field field : fields) {
            if (stringUtil.equals(fieldName, field.getName())) {
                result = field;
                break;
            }
        }
        return result;
    }

    @Override
    public List<Field> getFields(Class clazz, List<String> fieldNames) {
        List<Field> result = collectionUtil.emptyList();
        fieldNames = collectionUtil.distinct(fieldNames);
        if (clazz == null || collectionUtil.isEmpty(fieldNames)) {
            return result;
        }
        result = collectionUtil.emptyList();
        List<Field> fields = getFields(clazz);
        for (Field field : fields) {
            if (fieldNames.contains(field.getName())) {
                result.add(field);
            }
        }
        return result;
    }

    @Override
    public Constructor getContructor(Class clazz, Class... parameterTypes) {
        try {
            return clazz == null ? null : clazz.getConstructor(parameterTypes);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Object value(Object obj, Field field) {
        if (obj == null || field == null) {
            return null;
        }
        Class clazz = obj.getClass();
        Method getter = getGetter(clazz, field);
        return value(obj, getter);
    }

    @Override
    public Object value(Object obj, Method getter) {
        if (obj == null || getter == null) {
            return null;
        }
        try {
            Object value = getter.invoke(obj);
            return value;
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc="tool">
    private String builder(Class clazz) {
        StringBuilder sb = new StringBuilder();
        if (clazz == null) {
            return sb.toString();
        }
        boolean isInterface = Modifier.isInterface(clazz.getModifiers());
        if (isInterface) {
            return sb.toString();
        }
        boolean isAbstract = Modifier.isAbstract(clazz.getModifiers());
        Class sc = clazz.getSuperclass();
        boolean hasSuperClass = !Object.class.equals(sc);
        String className = clazz.getSimpleName();
        String scName = sc.getSimpleName();
        String scFullName = className.equals(scName) ? sc.getName() : scName;

        Field[] allFields = clazz.getDeclaredFields();
        List<Field> fields = new ArrayList();
        for (Field field : allFields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                fields.add(field);
            }
        }
        sb.append("public ").append(className).append("(){}");

        sb.append("protected ").append(className).append("(").append(className).append("Builder<?, ?> b) {");
        if (hasSuperClass) {
            sb.append("super(b);");
        }
        for (Field field : fields) {
            String fieldName = field.getName();
            sb.append("this.").append(fieldName).append("=").append("b.").append(fieldName).append(";");
        }
        sb.append("}");

        if (!isAbstract) {
            sb.append("public static ").append(className).append("Builder<?, ?> builder() {");
            sb.append("return new ").append(className).append("BuilderImpl();");
            sb.append("}");

            sb.append("private static final class ").append(className).append("BuilderImpl extends ").append(className).append("Builder<").append(className).append(", ").append(className).append("BuilderImpl> {");
            sb.append("private ").append(className).append("BuilderImpl(){}");
            sb.append("\n@Override\n")
                    .append("protected ").append(className).append("BuilderImpl self() {return this;}")
                    .append("\n@Override\n")
                    .append("public ").append(className).append(" build() {return new ").append(className).append("(this);}");
            sb.append("}");
        }

        sb.append("public static abstract class ").append(className).append("Builder<C extends ").append(className).append(", B extends ").append(className).append("Builder<C, B>> ");
        if (hasSuperClass) {
            sb.append(" extends ").append(scFullName).append(".").append(scName).append("Builder<C, B> {");
        } else {
            sb.append("{");
        }
        for (Field field : fields) {
            String fieldName = field.getName();
            String fieldClassName = field.getType().getSimpleName();
            sb.append("private ").append(fieldClassName).append(" ").append(fieldName).append(";");
        }
        if (hasSuperClass) {
            sb.append("\n@Override\nprotected abstract B self();");
            sb.append("\n@Override\npublic abstract C build();");
        } else {
            sb.append("protected abstract B self();");
            sb.append("public abstract C build();");
        }
        for (Field field : fields) {
            String fieldName = field.getName();
            String fieldClassName = field.getType().getSimpleName();
            sb.append("public B ").append(fieldName).append("(").append(fieldClassName).append(" ").append(fieldName).append(") {");
            sb.append("this.").append(fieldName).append(" = ").append(fieldName).append(";");
            sb.append("return self();");
            sb.append("}");
        }
        sb.append("}");

        return sb.toString();
    }

    private String builder(Class clazz, String name) {
        StringBuilder sb = new StringBuilder();
        name = stringUtil.trim(name);
        if (clazz == null || stringUtil.isEmpty(name)) {
            return sb.toString();
        }
        boolean isInterface = Modifier.isInterface(clazz.getModifiers());
        if (isInterface) {
            return sb.toString();
        }
        String className = clazz.getSimpleName();

        Field[] allFields = clazz.getDeclaredFields();
        List<Field> fields = new ArrayList();
        for (Field field : allFields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                fields.add(field);
            }
        }

        sb.append(className).append(".builder()");
        for (Field field : fields) {
            String fieldName = field.getName();
            Method getter = getGetter(clazz, field);
            sb.append("\r\n.").append(fieldName).append("(").append(name).append(".").append(getter.getName()).append("())");
        }
        sb.append(".build();");

        return sb.toString();
    }

    private String copy(Class clazz, String name) {
        StringBuilder sb = new StringBuilder();
        name = stringUtil.trim(name);
        if (clazz == null || stringUtil.isEmpty(name)) {
            return sb.toString();
        }

        Field[] allFields = clazz.getDeclaredFields();
        List<Field> fields = new ArrayList();
        for (Field field : allFields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                fields.add(field);
            }
        }

        for (Field field : fields) {
            String fieldName = field.getName();
            sb.append("\r\n").append(fieldName).append("=").append(name).append(".").append(fieldName).append(";");
        }

        return sb.toString();
    }

    private String clear(Class clazz, String name) {
        StringBuilder sb = new StringBuilder();
        name = stringUtil.trim(name);
        if (clazz == null || stringUtil.isEmpty(name)) {
            return sb.toString();
        }

        Field[] allFields = clazz.getDeclaredFields();
        List<Field> fields = new ArrayList();
        for (Field field : allFields) {
            if (!Modifier.isStatic(field.getModifiers())) {
                fields.add(field);
            }
        }

        for (Field field : fields) {
            String fieldName = field.getName();
            sb.append("\r\n").append(fieldName).append("=").append(name).append(";");
        }

        return sb.toString();
    }

    @Override
    public void builders(Class... clazzs) {
        if (clazzs == null) {
            return;
        }
        System.out.println("\n---------------------------------\n");
        for (Class clazz : clazzs) {
            System.out.println(builder(clazz));
            System.out.println("\n---------------------------------\n");
        }
    }

    @Override
    public void builders(String name, Class... clazzs) {
        if (clazzs == null) {
            return;
        }
        System.out.println("\n---------------------------------\n");
        for (Class clazz : clazzs) {
            System.out.println(builder(clazz, name));
            System.out.println("\n---------------------------------\n");
        }
    }

    @Override
    public void copys(String name, Class... clazzs) {
        if (clazzs == null) {
            return;
        }
        System.out.println("\n---------------------------------\n");
        for (Class clazz : clazzs) {
            System.out.println(copy(clazz, name));
            System.out.println("\n---------------------------------\n");
        }
    }

    @Override
    public void clears(String name, Class... clazzs) {
        if (clazzs == null) {
            return;
        }
        System.out.println("\n---------------------------------\n");
        for (Class clazz : clazzs) {
            System.out.println(clear(clazz, name));
            System.out.println("\n---------------------------------\n");
        }
    }
    //</editor-fold>
}
