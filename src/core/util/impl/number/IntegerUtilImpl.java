package core.util.impl.number;

import core.module.ModuleFactory;
import core.util.CollectionUtil;
import core.util.NumberUtil;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class IntegerUtilImpl implements NumberUtil<Integer> {

    private final CollectionUtil collectionUtil = ModuleFactory.getFactory().collectionUtil();
    private static final Integer ZERO = 0;

    @Override
    public Class<Integer> getType() {
        return Integer.class;
    }

    @Override
    public boolean isNull(Integer n) {
        return n == null;
    }

    @Override
    public <V> Integer parse(V v) {
        if (v == null) {
            return null;
        }
        if (v instanceof Integer) {
            return (Integer) v;
        }
        if (v instanceof Number) {
            return ((Number) v).intValue();
        }
        Number n;
        try {
            n = new BigDecimal(String.valueOf(v).trim());
        } catch (NumberFormatException e) {
            return null;
        }
        return n.intValue();
    }

    @Override
    public <V> List<Integer> parse(Collection<V> vs) {
        List<Integer> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(vs)) {
            return result;
        }
        for (V v : vs) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public <V> List<Integer> parse(V... av) {
        List<Integer> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(av)) {
            return result;
        }
        for (V v : av) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public Integer zero() {
        return ZERO;
    }

    @Override
    public Integer nvl(Integer n) {
        return n == null ? ZERO : n;
    }

    @Override
    public Integer nvl(Integer n, Integer nvl) {
        return n == null ? nvl : n;
    }

    @Override
    public boolean equals(Integer a, Integer b) {
        return (a == null && b == null) || (a != null && a.equals(b)) || (b != null && b.equals(a));
    }

    @Override
    public Integer abs(Integer n) {
        return n == null ? null : Math.abs(n);
    }

    @Override
    public Integer round(Integer n, int scale) {
        if (isNull(n)) {
            return n;
        }
        double f = Math.pow(10D, scale);
        return parse(Math.round(n.doubleValue() * f) / f);
    }

    @Override
    public Integer random(Integer min, Integer max) {
        Random random = new Random();
        int nextInt = random.nextInt((max - min) + 1) + min;
        return nextInt;
    }
}
