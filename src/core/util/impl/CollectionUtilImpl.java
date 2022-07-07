package core.util.impl;

import core.util.CollectionUtil;

import java.util.*;

public class CollectionUtilImpl implements CollectionUtil {
    @Override
    public <E> List<E> emptyList() {
        return new ArrayList<E>();
    }

    @Override
    public <E> boolean isEmpty(Collection<E> c) {
        return c == null || c.isEmpty();
    }

    @Override
    public <E> boolean isEmpty(E... a) {
        return a == null || a.length == 0;
    }

    @Override
    public <E> List<E> nvl(List<E> l) {
        l = (List<E>) (l == null ? emptyList() : l);
        l.removeAll(Collections.singleton(null));
        return l;
    }

    @Override
    public <E> List<E> toList(Collection<E> c) {
        List<E> l = (List<E>) (c == null ? emptyList() : new ArrayList<E>(c));
        l = nvl(l);
        return l;
    }

    @Override
    public <E> List<E> toList(E... a) {
        List<E> l = (List<E>) (a == null ? emptyList() : new ArrayList<E>(Arrays.asList(a)));
        l = nvl(l);
        return l;
    }

    @Override
    public <E> List<E> distinct(Collection<E> c) {
        List<E> l = toList(c);
        l.removeAll(Collections.singleton(null));
        l = new ArrayList<E>(new HashSet<E>(c));
        return l;
    }

    @Override
    public <E> List<E> distinct(E... a) {
        return distinct(toList(a));
    }

    @Override
    public <E> List<E> in(Collection<E> a, Collection<E> b) {
        List<E> result = emptyList();
        if (isEmpty(a) || isEmpty(b)) {
            return result;
        }
        List<E> aa = distinct(a);
        List<E> bb = distinct(b);
        for (E e : aa) {
            if (bb.contains(e)) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public <E> List<E> minus(Collection<E> a, Collection<E> b) {
        List<E> result = emptyList();
        if (isEmpty(a) || isEmpty(b)) {
            return result;
        }
        List<E> aa = distinct(a);
        List<E> bb = toList(b);
        for (E e : aa) {
            if (!bb.contains(e)) {
                result.add(e);
            }
        }
        return result;
    }

    @Override
    public <E> boolean equals(Collection<E> a, Collection<E> b) {
        return isEmpty(minus(a, b)) && isEmpty(minus(b, a));
    }

    @Override
    public <E> List<E> plus(Collection<E> a, Collection<E> b) {
        List<E> aa = toList(a);
        List<E> bb = toList(b);
        aa.addAll(bb);
        return aa;
    }

    @Override
    public <E> List<E> plus(Collection<Collection<E>> cc) {
        List<E> result = emptyList();
        if (isEmpty(cc)) {
            return result;
        }
        List<Collection<E>> lc = toList(cc);
        for (Collection<E> c : lc) {
            result.addAll(toList(c));
        }
        result = distinct(result);
        return result;
    }

    @Override
    public <E> List<E> plus(Collection<E>... ac) {
        List<E> result = emptyList();
        if (isEmpty(ac)) {
            return result;
        }
        List<Collection<E>> lc = toList(ac);
        for (Collection<E> c : lc) {
            result.addAll(toList(c));
        }
        result = distinct(result);
        return result;
    }

    @Override
    public <E> boolean contains(E v, Collection<E> c) {
        List<E> l = toList(c);
        return l.contains(v);
    }

    @Override
    public <E> boolean contains(E v, E... a) {
        List<E> l = toList(a);
        return l.contains(v);
    }

    @Override
    public <E> List<E> page(Collection<E> c, Integer page, Integer pageSize) {
        page = page == null || page < 0 ? 0 : page;
        pageSize = pageSize == null || pageSize < 0 ? 0 : pageSize;
        List<E> r = emptyList();
        List<E> l = toList(c);

        if (page < 0 || pageSize < 1) {
            return r;
        }
        int t = l.size();
        int b = catchBegin(page, pageSize);
        int e = catchEnd(t, page, pageSize);

        for (int i = b; i <= e; i++) {
            r.add(l.get(i));
        }
        return r;
    }

    private Integer catchBegin(int page, int pageSize) {
        return page * pageSize;
    }

    private Integer catchEnd(int total, int page, int pageSize) {
        int e = catchEnd(page, pageSize);
        if (e >= total) {
            e = total - 1;
        }
        return e;
    }

    private Integer catchEnd(int page, int pageSize) {
        return ((page + 1) * pageSize) - 1;
    }

    @Override
    public <E> E get(Collection<E> c) {
        List<E> es = toList(c);
        return es.isEmpty() ? null : es.get(0);
    }
}
