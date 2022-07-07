package core.util;

import core.cache.CacheKey;

public interface CacheBuilder {

    CacheBuilder clear();

    CacheBuilder clear(CacheKey key);

    <V> CacheBuilder put(CacheKey key, V value);

    <V> V get(CacheKey key);
}
