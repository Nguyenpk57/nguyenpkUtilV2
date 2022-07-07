package core.util;

import java.util.Collection;
import java.util.List;

public interface NumberUtil<N extends Number> {
    Class<N> getType();

    boolean isNull(N n);

    <V> N parse(V v);

    <V> List<N> parse(Collection<V> vs);

    <V> List<N> parse(V... av);

    N zero();

    N nvl(N n);

    N nvl(N n, N nvl);

    boolean equals(N a, N b);

    N abs(N n);

    N round(N n, int scale);

    N random(N min, N max);
}
