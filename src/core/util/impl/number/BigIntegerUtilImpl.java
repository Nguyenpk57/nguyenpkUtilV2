package core.util.impl.number;

import core.module.ModuleFactory;
import core.util.CollectionUtil;
import core.util.NumberUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.List;

public class BigIntegerUtilImpl implements NumberUtil<BigInteger> {

    private final CollectionUtil collectionUtil = ModuleFactory.getFactory().collectionUtil();

    @Override
    public Class<BigInteger> getType() {
        return BigInteger.class;
    }

    @Override
    public boolean isNull(BigInteger n) {
        return n == null;
    }

    @Override
    public <V> BigInteger parse(V v) {
        if (v instanceof BigDecimal) {
            return ((BigDecimal) v).toBigInteger();
        }
        if (v instanceof BigInteger) {
            return (BigInteger) v;
        }
        if (v instanceof Number) {
            return new BigInteger(String.valueOf(((Number) v).longValue()));
        }
        Number n;
        try {
            n = new BigDecimal(String.valueOf(v).trim());
        } catch (NumberFormatException e) {
            return null;
        }
        return new BigInteger(String.valueOf(n.longValue()));

    }

    @Override
    public <V> List<BigInteger> parse(Collection<V> vs) {
        List<BigInteger> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(vs)) {
            return result;
        }
        for (V v : vs) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public <V> List<BigInteger> parse(V... av) {
        List<BigInteger> result = collectionUtil.emptyList();
        if (collectionUtil.isEmpty(av)) {
            return result;
        }
        for (V v : av) {
            result.add(parse(v));
        }
        return result;
    }

    @Override
    public BigInteger zero() {
        return BigInteger.ZERO;
    }

    @Override
    public BigInteger nvl(BigInteger n) {
        return n == null ? BigInteger.ZERO : n;
    }

    @Override
    public BigInteger nvl(BigInteger n, BigInteger nvl) {
        return n == null ? nvl : n;
    }

    @Override
    public boolean equals(BigInteger a, BigInteger b) {
        return (a == null && b == null) || (a != null && a.equals(b)) || (b != null && b.equals(a));
    }

    @Override
    public BigInteger abs(BigInteger n) {
        return n == null ? null : n.abs();
    }

    @Override
    public BigInteger round(BigInteger bigInteger, int scale) {
        return null;
    }

    @Override
    public BigInteger random(BigInteger min, BigInteger max) {
        return null;
    }
}
