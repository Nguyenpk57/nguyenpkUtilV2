package core.cache;

import core.data.BaseData;
import core.type.CacheName;

import java.util.Date;

public class CacheKey extends BaseData {

    private static final long serialVersionUID = 4590066832132670826L;

    private CacheName name;
    private String key;
    private Integer ttl;//second
    private Date startTime;
    private Date expireTime;
    private Date endTime;

    public CacheKey() {
    }

    protected CacheKey(CacheKeyBuilder<?, ?> b) {
        super(b);
        this.name = b.name;
        this.key = b.key;
        this.ttl = b.ttl;
        this.startTime = b.startTime;
        this.expireTime = b.expireTime;
        this.endTime = b.endTime;
    }

    public static CacheKeyBuilder<?, ?> builder() {
        return new CacheKeyBuilderImpl();
    }

    private static final class CacheKeyBuilderImpl extends CacheKeyBuilder<CacheKey, CacheKeyBuilderImpl> {

        private CacheKeyBuilderImpl() {
        }

        @Override
        protected CacheKeyBuilderImpl self() {
            return this;
        }

        @Override
        public CacheKey build() {
            return new CacheKey(this);
        }
    }

    public static abstract class CacheKeyBuilder<C extends CacheKey, B extends CacheKeyBuilder<C, B>> extends BaseData.BaseDataBuilder<C, B> {

        private CacheName name;
        private String key;
        private Integer ttl;
        private Date startTime;
        private Date expireTime;
        private Date endTime;

        @Override
        protected abstract B self();

        @Override
        public abstract C build();

        public B name(CacheName name) {
            this.name = name;
            return self();
        }

        public B key(String key) {
            this.key = key;
            return self();
        }

        public B ttl(Integer ttl) {
            this.ttl = ttl;
            return self();
        }

        public B startTime(Date startTime) {
            this.startTime = startTime;
            return self();
        }

        public B expireTime(Date expireTime) {
            this.expireTime = expireTime;
            return self();
        }

        public B endTime(Date endTime) {
            this.endTime = endTime;
            return self();
        }
    }

    public CacheName getName() {
        return name;
    }

    public void setName(CacheName name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getTtl() {
        return ttl;
    }

    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}

