package core.log.factory.impl;

import core.log.Logger;
import core.log.factory.LoggerAbstractFactory;
import core.log.impl.ApacheLogger;

public class ApacheLoggerFactoryImpl implements LoggerAbstractFactory<org.apache.log4j.Logger> {

    @Override
    public Logger<org.apache.log4j.Logger> getLogger(Class clazz) {
        return new ApacheLogger(clazz);
    }

    @Override
    public Logger<org.apache.log4j.Logger> getLogger(org.apache.log4j.Logger logger) {
        return new ApacheLogger(logger);
    }
}
