package core.log;

public interface Logger<T> {

    String name();

    T logger();

    void debug(Object message);

    void debug(Object message, Throwable t);

    void info(Object message);

    void info(Object message, Throwable t);

    void error(Object message);

    void error(Object message, Throwable t);
}
