package core.util.impl;

import core.module.ModuleFactory;
import core.util.PatternBuilder;
import core.util.StringUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

public class PatternBuilderImpl implements PatternBuilder {
    private static final Object LOCK = new Object();
    private static final Map<String, Pattern> PATTERNS = new ConcurrentHashMap<String, Pattern>();
    private static final StringUtil stringUtil = ModuleFactory.getFactory().stringUtil();

    @Override
    public Pattern get(String regex) {
        if (stringUtil.isEmpty(regex)) {
            return null;
        }
        Pattern pattern = PATTERNS.get(regex);
        if (pattern == null) {
            synchronized (LOCK) {
                pattern = Pattern.compile(regex);
                PATTERNS.put(regex, pattern);
            }
        }
        return pattern;
    }
}
