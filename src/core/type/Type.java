package core.type;

import java.io.Serializable;

public interface Type<V> extends Serializable {

    String key();

    V value();

    String label();
}
