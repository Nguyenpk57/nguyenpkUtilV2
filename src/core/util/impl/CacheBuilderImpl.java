package core.util.impl;

import core.cache.Cache;
import core.cache.CacheKey;
import core.log.Logger;
import core.module.ModuleFactory;
import core.type.CacheName;
import core.util.*;

import java.util.Date;

public class CacheBuilderImpl implements CacheBuilder {

    private static volatile CacheBuilderImpl instance;
    private static final Object LOCK = new Object();
    private static final Integer DEFAULT_TTL = 7 * 24 * 60 * 60;//7 day
    protected final StringUtil stringUtil = ModuleFactory.getFactory().stringUtil();
    protected final DateTimeUtil dateTimeUtil = ModuleFactory.getFactory().dateTimeUtil();
    protected final NumberUtil<Integer> integerUtil = ModuleFactory.getFactory().integerUtil();

    protected Logger logger;
    protected final MapBuilder caches = ModuleFactory.getFactory().mapBuilder();

    private CacheBuilderImpl() {
    }

    public static final CacheBuilder instance() {
        if (instance == null) {
            synchronized (LOCK) {
                if (instance == null) {
                    instance = new CacheBuilderImpl();
                }
            }
        }
        return instance;
    }

    @Override
    public CacheBuilder clear() {
        synchronized (LOCK) {
            caches.clear();
        }
        return this;
    }

    @Override
    public CacheBuilder clear(CacheKey key) {
        return this;
    }

    @Override
    public <V> CacheBuilder put(CacheKey key, V value) {
        if (key == null) {
            return null;
        }
        CacheName name = key.getName();
        if (CacheName.isInvalid(name)) {
            return null;
        }

        String dataKey = stringUtil.nvl(key.getKey());

        Integer ttl = integerUtil.nvl(key.getTtl());
        ttl = ttl > 0 ? ttl : DEFAULT_TTL;

        Date runTime = new Date();
        Date expireTime = dateTimeUtil.addSecond(runTime, ttl);

        key = CacheKey.builder()
                .name(name)
                .key(dataKey)
                .ttl(ttl)
                .startTime(runTime)
                .expireTime(expireTime)
                .build();

        Cache<V> cache = (Cache<V>) Cache.builder()
                .key(key)
                .value(value)
                .build();

        String finalKey = stringUtil.format("{0}_{1}", name.value(), dataKey);
        synchronized (LOCK) {
            caches.put(finalKey, cache);
        }

        return this;
    }

    @Override
    public <V> V get(CacheKey key) {
        if (key == null) {
            return null;
        }
        CacheName name = key.getName();
        if (CacheName.isInvalid(name)) {
            return null;
        }

        String dataKey = stringUtil.nvl(key.getKey());
        String finalKey = stringUtil.format("{0}_{1}", name.value(), dataKey);

        Cache<V> cache = (Cache<V>) caches.get(finalKey);
        if (cache == null) {
            return null;
        }

        CacheKey cacheKey = cache.getKey();
        if (cacheKey == null) {
            remove(finalKey);
            return null;
        }
        Date expireTime = cacheKey.getExpireTime();
        Date runTime = new Date();
        if (expireTime == null || expireTime.before(runTime)) {
            remove(finalKey);
            return null;
        }

        return cache.getValue();
    }

    private void remove(String key) {
        synchronized (LOCK) {
            caches.remove(key);
        }
    }
}
