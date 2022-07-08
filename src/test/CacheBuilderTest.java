package test;

import core.cache.CacheKey;
import core.module.ModuleFactory;
import core.type.CacheName;
import core.util.CacheBuilder;

public class CacheBuilderTest {
    private final CacheBuilder cacheBuilder = ModuleFactory.getFactory().cacheBuilder();

    public static void main(String[] args) {
        CacheBuilderTest cacheBuilderTest = new CacheBuilderTest();
        cacheBuilderTest.test();
    }

    public void test() {
        CacheKey cacheKey = CacheKey.builder()
                .name(CacheName.CUST_LENGTH)
                .build();
        cacheBuilder.put(cacheKey, 7);
        System.out.println("cacheBuilder: " + cacheBuilder.get(cacheKey));
    }
}
