package core.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public interface ReflectUtil {

    String getStack();

    String getStack(Throwable e);

    List<Class> getSuperclasses(Class cl);

    Class getLastSuperclass(Class cl);

    List<Method> getMethods(Class clazz);

    List<Method> getGetters(Class clazz);

    List<Method> getSetters(Class clazz);

    Method getMethod(Class clazz, String methodName);

    Method getGetter(Class clazz, Field field);

    Method getSetter(Class clazz, Field field);

    List<Field> getFields(Class clazz);

    Field getField(Class clazz, String fieldName);

    List<Field> getFields(Class clazz, List<String> fieldNames);

    Constructor getContructor(Class clazz, Class... parameterTypes);

    Object value(Object obj, Field field);

    Object value(Object obj, Method getter);

    //<editor-fold defaultstate="collapsed" desc="tool">
    void builders(Class... clazzs);

    void builders(String name, Class... clazzs);

    void copys(String name, Class... clazzs);

    void clears(String name, Class... clazzs);
    //</editor-fold>
}
