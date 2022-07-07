package core.util.impl.number;


import core.module.ModuleFactory;
import core.util.CollectionUtil;
import core.util.NumberUtil;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class FloatUtilImpl implements NumberUtil<Float> {

    private final CollectionUtil collectionUtil = ModuleFactory.getFactory().collectionUtil();
    private static final Float ZERO = 0F;

    @Override
    public Class<Float> getType() {
        return Float.class;
    }

    @Override
    public boolean isNull(Float n) {
        return n == null;
    }

    @Override
    public <V> Float parse(V v) {
        if (v == null) {
            return null;
        }
        if (v instanceof Float) {
            return (Float) v;
        }
        if (v instanceof Number) {
            return ((Number) v).floatValue();
        }
        Number n;
        try {
            n = new BigDecimal(String.valueOf(v).trim());
        } catch (NumberFormatException e) {
            return null;
        }
        return n.floatValue();
    }

    @Override
    public <V> List<Float> parse(Collection<V> vs) {
        List<Float> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(vs)) {
            return result;
        }
        for (V v : vs) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public <V> List<Float> parse(V... av) {
        List<Float> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(av)) {
            return result;
        }
        for (V v : av) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public Float zero() {
        return ZERO;
    }

    @Override
    public Float nvl(Float n) {
        return n == null ? 0F : n;
    }

    @Override
    public Float nvl(Float n, Float nvl) {
        return n == null ? nvl : n;
    }

    @Override
    public boolean equals(Float a, Float b) {
        return (a == null && b == null) || (a != null && a.equals(b)) || (b != null && b.equals(a));
    }

    @Override
    public Float abs(Float n) {
        return n == null ? null : Math.abs(n);
    }

    @Override
    public Float round(Float n, int scale) {
        if (isNull(n)) {
            return n;
        }
        double f = Math.pow(10D, scale);
        return parse(Math.round(n.doubleValue() * f) / f);
    }

    @Override
    public Float random(Float min, Float max) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
