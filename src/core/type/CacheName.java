package core.type;

import core.module.ModuleFactory;
import core.util.StringUtil;

public enum CacheName implements EnumType<String, CacheName>, Type<String> {
    INVALID(StringUtil.BLANK),
    CUST_LENGTH("CUST_LENGTH");

    private final StringUtil stringUtil = ModuleFactory.getFactory().stringUtil();

    private final String value;

    private CacheName(String value) {
        this.value = value;
    }

    @Override
    public String key() {
        return name();
    }

    @Override
    public String value() {
        return value;
    }

    @Override
    public String label() {
        return name();
    }

    @Override
    public CacheName name(String name) {
        for (CacheName t : CacheName.values()) {
            if (stringUtil.equalsIgnoreCase(t.name(), name)) {
                return t;
            }
        }
        return INVALID;
    }

    @Override
    public CacheName value(String value) {
        for (CacheName t : CacheName.values()) {
            if (stringUtil.equalsIgnoreCase(t.value, value)) {
                return t;
            }
        }
        return INVALID;
    }

    @Override
    public String value(CacheName t) {
        return t == null ? INVALID.value : t.value();
    }

    @Override
    public Class<String> clazz() {
        return String.class;
    }

    public static CacheName nvl(CacheName t) {
        return t == null ? INVALID : t;
    }

    public static CacheName fromName(String name) {
        return INVALID.name(name);
    }

    public static CacheName fromValue(String value) {
        return INVALID.value(value);
    }

    public static String toValue(CacheName t) {
        return INVALID.value(t);
    }

    public static boolean isValid(CacheName t) {
        return !isInvalid(t);
    }

    public static boolean isInvalid(CacheName t) {
        return t == null || INVALID.equals(t);
    }

    public static boolean equals(CacheName a, CacheName b) {
        return a == b;
    }
}
