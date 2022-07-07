package core.util.format.impl;

import core.module.ModuleFactory;
import core.util.format.Formatter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class SimpleDateFormatterImpl implements Formatter {

    private final Locale locale = ModuleFactory.getFactory().locale();

    @Override
    public Format get(String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern, locale);
        return simpleDateFormat;
    }
}
