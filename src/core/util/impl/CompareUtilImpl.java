package core.util.impl;

import core.util.CompareUtil;

public class CompareUtilImpl implements CompareUtil {

    @Override
    public <A, B> boolean equal(A a, B b) {
        return (a == null && b == null) || (a != null && a.equals(b)) || (b != null && b.equals(a));
    }
}
