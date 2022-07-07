package core.type;

public interface EnumType<V, E extends Enum<E>> extends Type<V> {

    E name(String name);

    E value(V value);

    V value(E t);

    Class<V> clazz();
}
