package core.util.impl.number;

import core.module.ModuleFactory;
import core.util.CollectionUtil;
import core.util.NumberUtil;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class BigDecimalUtilImpl implements NumberUtil<BigDecimal> {

    private final CollectionUtil collectionUtil = ModuleFactory.getFactory().collectionUtil();

    @Override
    public Class<BigDecimal> getType() {
        return BigDecimal.class;
    }

    @Override
    public boolean isNull(BigDecimal n) {
        return n == null;
    }

    @Override
    public <V> BigDecimal parse(V v) {
        if (v == null) {
            return null;
        }
        if (v instanceof BigDecimal) {
            return (BigDecimal) v;
        }
        try {
            return new BigDecimal(String.valueOf(v).trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public <V> List<BigDecimal> parse(Collection<V> vs) {
        List<BigDecimal> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(vs)) {
            return result;
        }
        for (V v : vs) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public <V> List<BigDecimal> parse(V... av) {
        List<BigDecimal> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(av)) {
            return result;
        }
        for (V v : av) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public BigDecimal zero() {
        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal nvl(BigDecimal n) {
        return n == null ? BigDecimal.ZERO : n;
    }

    @Override
    public BigDecimal nvl(BigDecimal n, BigDecimal nvl) {
        return n == null ? nvl : n;
    }

    @Override
    public boolean equals(BigDecimal a, BigDecimal b) {
        return (a == null && b == null) || (a != null && a.equals(b)) || (b != null && b.equals(a));
    }

    @Override
    public BigDecimal abs(BigDecimal n) {
        return n == null ? null : n.abs();
    }

    @Override
    public BigDecimal round(BigDecimal n, int scale) {
        if (isNull(n)) {
            return n;
        }
        double f = Math.pow(10, scale);
        return parse(Math.round(n.doubleValue() * f) / f);
    }

    @Override
    public BigDecimal random(BigDecimal min, BigDecimal max) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
