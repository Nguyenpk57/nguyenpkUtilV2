package core.util.impl;

import core.module.ModuleFactory;
import core.util.PatternBuilder;
import core.util.StringUtil;
import core.util.ValidationUtil;

public class ValidationUtilImpl implements ValidationUtil {
    
    private final StringUtil stringUtil = ModuleFactory.getFactory().stringUtil();
    private final PatternBuilder patternBuilder = ModuleFactory.getFactory().patternBuilder();
    
    @Override
    public <V> boolean isNull(V v) {
        return v == null;
    }

    @Override
    public <V> boolean isNumber(V v) {
        return false;
    }

    @Override
    public boolean isIsdn(String v) {
        return false;
    }

    @Override
    public boolean isEmail(String v) {
        return false;
    }

    @Override
    public boolean matches(String value, String regex) {
        return (stringUtil.isEmpty(value) || stringUtil.isEmpty(regex)) ? false : patternBuilder.get(regex).matcher(value).matches();
    }
}
