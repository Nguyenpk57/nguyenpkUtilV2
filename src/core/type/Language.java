package core.type;

import core.module.ModuleFactory;
import core.util.StringUtil;

public enum Language implements EnumType<String, Language>, Type<String> {
    INVALID(StringUtil.BLANK),
    VI("vi"),
    EN("en"),
    ES("es");

    private final StringUtil stringUtil = ModuleFactory.getFactory().stringUtil();

    private final String value;

    private Language(String value) {
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
    public Language name(String name) {
        for (Language t : Language.values()) {
            if (stringUtil.equalsIgnoreCase(t.name(), name)) {
                return t;
            }
        }
        return INVALID;
    }

    @Override
    public Language value(String value) {
        for (Language t : Language.values()) {
            if (stringUtil.equalsIgnoreCase(t.value, value)) {
                return t;
            }
        }
        return INVALID;
    }

    @Override
    public String value(Language t) {
        return t == null ? INVALID.value : t.value();
    }

    @Override
    public Class<String> clazz() {
        return String.class;
    }

    public static Language nvl(Language t) {
        return t == null ? INVALID : t;
    }

    public static Language fromName(String name) {
        return INVALID.name(name);
    }

    public static Language fromValue(String value) {
        return INVALID.value(value);
    }

    public static String toValue(Language t) {
        return INVALID.value(t);
    }

    public static boolean isValid(Language t) {
        return !isInvalid(t);
    }

    public static boolean isInvalid(Language t) {
        return t == null || INVALID.equals(t);
    }

    public static boolean equals(Language a, Language b) {
        return a == b;
    }
}