package core.util.format.impl;

import core.util.format.Formatter;

import java.text.DecimalFormat;

public class DecimalFormatterImpl implements Formatter<DecimalFormat> {
    @Override
    public DecimalFormat get(String pattern) {
        DecimalFormat f = new DecimalFormat(pattern);
        return f;
    }
}
