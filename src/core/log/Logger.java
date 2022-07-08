package core.log;

public interface Logger<T> {

    String name();

    T logger();

    //<editor-fold defaultstate="collapsed" desc="log">
    void debug(Object message);

    void debug(Object message, Throwable t);

    void info(Object message);

    void info(Object message, Throwable t);

    void error(Object message);

    void error(Object message, Throwable t);
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="logs">
    void errors(Object... values);

    void errors(Throwable t, Object... values);

    void infos(Object... values);

    void infos(Throwable t, Object... values);

    void warns(Object... values);

    void warns(Throwable t, Object... values);

    void debugs(Object... values);

    void debugs(Throwable t, Object... values);
    //</editor-fold>
}
