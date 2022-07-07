package core.util.impl.number;

import core.module.ModuleFactory;
import core.util.CollectionUtil;
import core.util.NumberUtil;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class DoubleUtilImpl implements NumberUtil<Double> {

    private final CollectionUtil collectionUtil = ModuleFactory.getFactory().collectionUtil();
    private static final Double ZERO = 0D;

    @Override
    public Class<Double> getType() {
        return Double.class;
    }

    @Override
    public boolean isNull(Double n) {
        return n == null;
    }

    @Override
    public <V> Double parse(V v) {
        if (v == null) {
            return null;
        }
        if (v instanceof Double) {
            return (Double) v;
        }
        if (v instanceof Number) {
            return ((Number) v).doubleValue();
        }
        Number n;
        try {
            n = new BigDecimal(String.valueOf(v).trim());
        } catch (NumberFormatException e) {
            return null;
        }
        return n.doubleValue();
    }

    @Override
    public <V> List<Double> parse(Collection<V> vs) {
        List<Double> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(vs)) {
            return result;
        }
        for (V v : vs) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public <V> List<Double> parse(V... av) {
        List<Double> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(av)) {
            return result;
        }
        for (V v : av) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public Double zero() {
        return ZERO;
    }

    @Override
    public Double nvl(Double n) {
        return n == null ? ZERO : n;
    }

    @Override
    public Double nvl(Double n, Double nvl) {
        return n == null ? nvl : n;
    }

    @Override
    public boolean equals(Double a, Double b) {
        return (a == null && b == null) || (a != null && a.equals(b)) || (b != null && b.equals(a));
    }

    @Override
    public Double abs(Double n) {
        return n == null ? null : Math.abs(n);
    }

    @Override
    public Double round(Double n, int scale) {
        if (isNull(n)) {
            return n;
        }
        double f = Math.pow(10D, scale);
        return parse(Math.round(n * f) / f);
    }

    @Override
    public Double random(Double min, Double max) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
