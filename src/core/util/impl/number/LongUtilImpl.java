package core.util.impl.number;


import core.module.ModuleFactory;
import core.util.CollectionUtil;
import core.util.NumberUtil;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public class LongUtilImpl implements NumberUtil<Long> {

    private final CollectionUtil collectionUtil = ModuleFactory.getFactory().collectionUtil();
    private static final Long ZERO = 0L;

    @Override
    public Class<Long> getType() {
        return Long.class;
    }

    @Override
    public boolean isNull(Long n) {
        return n == null;
    }

    @Override
    public <V> Long parse(V v) {
        if (v == null) {
            return null;
        }
        if (v instanceof Long) {
            return (Long) v;
        }
        if (v instanceof Number) {
            return ((Number) v).longValue();
        }
        Number n;
        try {
            n = new BigDecimal(String.valueOf(v).trim());
        } catch (NumberFormatException e) {
            return null;
        }
        return n.longValue();
    }

    @Override
    public <V> List<Long> parse(Collection<V> vs) {
        List<Long> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(vs)) {
            return result;
        }
        for (V v : vs) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public <V> List<Long> parse(V... av) {
        List<Long> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(av)) {
            return result;
        }
        for (V v : av) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public Long zero() {
        return ZERO;
    }

    @Override
    public Long nvl(Long n) {
        return n == null ? ZERO : n;
    }

    @Override
    public Long nvl(Long n, Long nvl) {
        return n == null ? nvl : n;
    }

    @Override
    public boolean equals(Long a, Long b) {
        return (a == null && b == null) || (a != null && a.equals(b)) || (b != null && b.equals(a));
    }

    @Override
    public Long abs(Long n) {
        return n == null ? null : Math.abs(n);
    }

    @Override
    public Long round(Long n, int scale) {
        if (isNull(n)) {
            return n;
        }
        double f = Math.pow(10D, scale);
        return parse(Math.round(n.doubleValue() * f) / f);
    }

    @Override
    public Long random(Long min, Long max) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
