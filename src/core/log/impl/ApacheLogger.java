package core.log.impl;

import core.log.Logger;
import core.result.Stack;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApacheLogger implements Logger<org.apache.log4j.Logger> {

    private final org.apache.log4j.Logger logger;
    protected String name;

    public ApacheLogger(Class clazz) {
        name = this.getClass().getName();
        this.logger = org.apache.log4j.Logger.getLogger(clazz);
    }

    public ApacheLogger(org.apache.log4j.Logger logger) {
        name = this.getClass().getName();
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

    //<editor-fold defaultstate="collapsed" desc="logs">

    @Override
    public void errors(Object... values) {
        String log = new Stack().catchInfo() + ":" + catchLog(values);
        this.logger.error(log);
        print(log);
    }

    @Override
    public void errors(Throwable t, Object... values) {
        String log = new Stack().catchInfo() + ":" + catchLog(values);
        this.logger.error(log, t);
        print(log, t);
    }

    @Override
    public void infos(Object... values) {
        String log = new Stack().catchInfo() + ":" + catchLog(values);
        this.logger.info(log);
        print(log);
    }

    @Override
    public void infos(Throwable t, Object... values) {
        String log = new Stack().catchInfo() + ":" + catchLog(values);
        this.logger.info(log, t);
        print(log, t);
    }

    @Override
    public void warns(Object... values) {
        String log = new Stack().catchInfo() + ":" + catchLog(values);
        this.logger.warn(log);
        print(log);
    }

    @Override
    public void warns(Throwable t, Object... values) {
        String log = new Stack().catchInfo() + ":" + catchLog(values);
        this.logger.warn(log, t);
        print(log, t);
    }

    @Override
    public void debugs(Object... values) {
        String log = new Stack().catchInfo() + ":" + catchLog(values);
        this.logger.debug(log);
        print(log);
    }

    @Override
    public void debugs(Throwable t, Object... values) {
        String log = new Stack().catchInfo() + ":" + catchLog(values);
        this.logger.debug(log, t);
        print(log, t);
    }
    //</editor-fold>


    private String catchLog(Object... values) {
        String result = "null";
        if (values == null) {
            return result;
        }
        int length = values.length;
        if (length == 0) {
            return result;
        }
        List<String> strs = new ArrayList<String>();
        int i = 0;
        while (i < length) {
            Object key = values[i];
            key = key == null ? "null" : String.valueOf(key).trim();
            int j = i + 1;
            if (j < length) {
                Object value = values[j];
                value = value == null ? "null" : String.valueOf(value).trim();
                strs.add(key + "=" + value);
                i = j + 1;
                continue;
            }
            strs.add(key + "=null");
            i = j + 1;
        }
        result = strs + "|";
        return result;
    }

    private void print(Object... objs) {
        if (objs != null) {
            for (Object obj : objs) {
                if (obj instanceof Throwable) {
                    ((Throwable) obj).printStackTrace();
                    continue;
                }
                System.out.println(StringUtils.join(DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss.SSS"),
                        StringUtils.join("LoggerImpl.print:", String.valueOf(obj))));
            }
        }

    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("name=").append(name).append(",")
                .append("additivity=").append(this.logger.getAdditivity()).append(",")
                .append("debugEnabled=").append(this.logger.isDebugEnabled()).append(",")
                .append("infoEnabled=").append(this.logger.isInfoEnabled()).append(",")
                .append("traceEnabled=").append(this.logger.isTraceEnabled())
                .toString();
    }
}
