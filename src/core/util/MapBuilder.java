package core.util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public interface MapBuilder<K, V> {

    MapBuilder<K, V> put(K key, V value);

    MapBuilder<K, V> put(Map<K, V> map);

    MapBuilder<K, V> put(MapBuilder<K, V> map);

    MapBuilder<K, V> clear();

    MapBuilder<K, V> remove(K key);

    boolean isEmpty();

    boolean containsKey(K key);

    V get(K key);

    Set<K> keySet();

    Collection<V> values();

    Map<K, V> map();
}
