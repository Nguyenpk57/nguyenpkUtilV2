package core.util.impl;

import core.module.ModuleFactory;
import core.util.CollectionUtil;
import core.util.StringUtil;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Random;

public class StringUtilImpl implements StringUtil {

    private final CollectionUtil collectionUtil = ModuleFactory.getFactory().collectionUtil();

    @Override
    public <V> String parse(V v) {
        if (v == null) {
            return BLANK;
        }
        if (v instanceof String) {
            return nvl((String) v);
        }
        return nvl(String.valueOf(v));
    }

    @Override
    public boolean isNull(String s) {
        return s == null;
    }

    @Override
    public boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    @Override
    public String nvl(String s) {
        return nvl(s, BLANK);
    }

    @Override
    public String nvl(String s, String nvl) {
        return s == null ? nvl : s.trim();
    }

    @Override
    public String trim(String s) {
        return nvl(s);
    }

    @Override
    public String trimAll(String s) {
        return trim(nvl(s).replaceAll("\\s+", " "));
    }

    @Override
    public String upper(String s) {
        return nvl(s).toUpperCase();
    }

    @Override
    public String lower(String s) {
        return nvl(s).toLowerCase();
    }

    @Override
    public String capitalize(String s) {
        s = lower(s);
        return isEmpty(s) ? BLANK : (s.substring(0, 1).toUpperCase() + s.substring(1));
    }

    @Override
    public boolean equals(String a, String b) {
        return nvl(a).equals(nvl(b));
    }

    @Override
    public boolean equalsIgnoreCase(String a, String b) {
        return nvl(a).equalsIgnoreCase(nvl(b));
    }

    @Override
    public <O> String format(String pattern, O... a) {
        //TODO Formatter<MessageFormat>
        return null;
    }

    @Override
    public <O> String join(String joiner, O... a) {
        if (collectionUtil.isEmpty(a)) {
            return BLANK;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append(a[i++]);
        int n = a.length;
        for (; i < n; i++) {
            sb.append(joiner).append(a[i]);
        }
        return sb.toString();
    }

    @Override
    public <O> String join(String joiner, List<O> l) {
        if (collectionUtil.isEmpty(l)) {
            return BLANK;
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append(l.get(i++));
        int n = l.size();
        for (; i < n; i++) {
            sb.append(joiner).append(l.get(i));
        }
        return sb.toString();
    }

    @Override
    public List<String> split(String s, String regex) {
        List<String> result = collectionUtil.emptyList();
        if (isEmpty(s)) {
            return result;
        }
        String[] a = s.trim().split(regex);
        return collectionUtil.toList(a);
    }

    @Override
    public String randomAlphanumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    @Override
    public String randomDigits(int length) {
        StringBuilder sb = new StringBuilder();
        if (length < 0) {
            return sb.toString();
        }
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(9));
        }
        return sb.toString();
    }
}
