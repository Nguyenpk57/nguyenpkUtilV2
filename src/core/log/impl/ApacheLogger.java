package core.log.impl;

import core.log.Logger;

public class ApacheLogger implements Logger<org.apache.log4j.Logger> {

    private final org.apache.log4j.Logger logger;

    public ApacheLogger(Class clazz) {
        this.logger = org.apache.log4j.Logger.getLogger(clazz);
    }

    public ApacheLogger(org.apache.log4j.Logger logger) {
        this.logger = logger;
    }

    @Override
    public String name() {
        return logger.getName();
    }

    @Override
    public org.apache.log4j.Logger logger() {
        return logger;
    }

    @Override
    public void debug(Object message) {
        logger.debug(message);
        print(message);
    }

    @Override
    public void debug(Object message, Throwable t) {
        logger.debug(message, t);
        print(message);
    }

    @Override
    public void info(Object message) {
        logger.info(message);
        print(message);
    }

    @Override
    public void info(Object message, Throwable t) {
        logger.info(message, t);
        print(message);
    }

    @Override
    public void error(Object message) {
        logger.error(message);
        print(message);
    }

    @Override
    public void error(Object message, Throwable t) {
        logger.error(message, t);
        print(message);
    }

    private void print(Object message) {
        System.out.println(message);
    }
}
