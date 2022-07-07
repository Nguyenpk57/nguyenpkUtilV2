package core.data;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BaseData implements Data {

    private static final long serialVersionUID = 4590066832132670826L;

    private Map map = new ConcurrentHashMap();

    public BaseData() {
    }

    protected BaseData(BaseDataBuilder<?, ?> b) {
        this.map = b.map;
    }

    public static abstract class BaseDataBuilder<C extends BaseData, B extends BaseDataBuilder<C, B>> {

        private Map map = new ConcurrentHashMap();

        protected abstract B self();

        public abstract C build();

        protected BaseDataBuilder(BaseData c) {
        }

        public B map(Map map) {
            this.map = map;
            return self();
        }

        protected BaseDataBuilder() {
        }
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public BaseData set(Object key, Object value) {
        if (key == null || value == null) {
            return this;
        }
        this.map.put(key, value);
        return this;
    }

    public BaseData set(Map map) {
        if (map == null) {
            return this;
        }
        this.map.putAll(map);
        return this;
    }

    public Object get(Object key) {
        return this.map.get(key);
    }
}
