package core.cache;

import core.data.BaseData;

public class Cache<V> extends BaseData {

    private static final long serialVersionUID = 4590066832132670826L;

    private CacheKey key;
    private V value;

    public Cache() {
    }

    protected Cache(CacheBuilder<V, ?, ?> b) {
        super(b);
        this.key = b.key;
        this.value = b.value;
    }

    public static <V> CacheBuilder<V, ?, ?> builder() {
        return new CacheBuilderImpl<V>();
    }

    private static final class CacheBuilderImpl<V> extends CacheBuilder<V, Cache<V>, CacheBuilderImpl<V>> {

        private CacheBuilderImpl() {
        }

        @Override
        protected CacheBuilderImpl<V> self() {
            return this;
        }

        @Override
        public Cache<V> build() {
            return new Cache<V>(this);
        }
    }

    public static abstract class CacheBuilder<V, C extends Cache<V>, B extends CacheBuilder<V, C, B>> extends BaseData.BaseDataBuilder<C, B> {

        private CacheKey key;
        private V value;

        @Override
        protected abstract B self();

        @Override
        public abstract C build();

        public B key(CacheKey key) {
            this.key = key;
            return self();
        }

        public B value(V value) {
            this.value = value;
            return self();
        }
    }

    public CacheKey getKey() {
        return key;
    }

    public void setKey(CacheKey key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
