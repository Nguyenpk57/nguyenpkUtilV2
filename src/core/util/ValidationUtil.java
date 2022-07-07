package core.util;

public interface ValidationUtil {

    <V> boolean isNull(V v);

    <V> boolean isNumber(V v);

    boolean isIsdn(String v);

    boolean isEmail(String v);

    boolean matches(String value, String regex);
}
