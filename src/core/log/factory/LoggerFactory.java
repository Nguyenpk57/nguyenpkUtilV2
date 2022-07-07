package core.log.factory;

import core.log.factory.impl.ApacheLoggerFactoryImpl;

public class LoggerFactory {

    private LoggerFactory() {
    }

    public static LoggerAbstractFactory getFactory() {
        return new ApacheLoggerFactoryImpl();
    }
}
