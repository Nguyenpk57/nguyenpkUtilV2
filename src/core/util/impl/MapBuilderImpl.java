package core.util.impl;

import core.util.MapBuilder;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MapBuilderImpl<K, V> implements MapBuilder<K, V> {

    protected Map<K, V> map = new ConcurrentHashMap<K, V>();

    @Override
    public MapBuilder<K, V> put(K key, V value) {
        if (value == null) {
            map.remove(key);
            return this;
        }
        map.put(key, value);
        return this;
    }

    @Override
    public MapBuilder<K, V> put(Map<K, V> map) {
        if (map == null) {
            return this;
        }
        for (K key : map.keySet()) {
            put(key, map.get(key));
        }
        return this;
    }

    @Override
    public MapBuilder<K, V> put(MapBuilder<K, V> map) {
        if (map == null) {
            return this;
        }
        return put(map.map());
    }

    @Override
    public MapBuilder<K, V> clear() {
        this.map.clear();
        return this;
    }

    @Override
    public MapBuilder<K, V> remove(K key) {
        this.map.remove(key);
        return this;
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            return false;
        }
        return map.containsKey(key);
    }

    @Override
    public V get(K key) {
        return key == null ? null : map.get(key);
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values();
    }

    @Override
    public Map<K, V> map() {
        return map;
    }
}