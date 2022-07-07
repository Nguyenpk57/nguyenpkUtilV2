package core.log.factory;

import core.log.Logger;

public interface LoggerAbstractFactory<T> {

    Logger<T> getLogger(Class clazz);

    Logger<T> getLogger(T logger);
}
