package core.util;

import java.util.Collection;
import java.util.List;

public interface CollectionUtil {

    <E> List<E> emptyList();

    <E> boolean isEmpty(Collection<E> c);

    <E> boolean isEmpty(E... a);

    <E> List<E> nvl(List<E> l);

    <E> List<E> toList(Collection<E> c);

    <E> List<E> toList(E... a);

    <E> List<E> distinct(Collection<E> c);

    <E> List<E> distinct(E... a);

    <E> List<E> in(Collection<E> a, Collection<E> b);

    <E> List<E> minus(Collection<E> a, Collection<E> b);

    <E> boolean equals(Collection<E> a, Collection<E> b);

    <E> List<E> plus(Collection<E> a, Collection<E> b);

    <E> List<E> plus(Collection<Collection<E>> cc);

    <E> List<E> plus(Collection<E>... ac);

    <E> boolean contains(E v, Collection<E> c);

    <E> boolean contains(E v, E... a);

    <E> List<E> page(Collection<E> c, Integer page, Integer pageSize);

    <E> E get(Collection<E> c);
}
