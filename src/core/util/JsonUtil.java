package core.util;

public interface JsonUtil {
    <O> String to(O o);

    <T> T from(String json, Class<T> type);
}
