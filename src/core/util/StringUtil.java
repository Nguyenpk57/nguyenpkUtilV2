package core.util;

import java.util.List;

public interface StringUtil {

    String BLANK = "";

    <V> String parse(V v);

    boolean isNull(String s);

    boolean isEmpty(String s);

    String nvl(String s);

    String nvl(String s, String nvl);

    String trim(String s);

    String trimAll(String s);

    String upper(String s);

    String lower(String s);

    String capitalize(String s);

    boolean equals(String a, String b);

    boolean equalsIgnoreCase(String a, String b);

    <O> String format(String pattern, O... a);

    <O> String join(String joiner, O... a);

    <O> String join(String joiner, List<O> l);

    List<String> split(String s, String regex);

    String randomAlphanumeric(int length);

    String randomDigits(int length);
}
