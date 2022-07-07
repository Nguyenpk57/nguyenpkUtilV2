package core.util.impl;

import com.google.gson.Gson;
import core.util.JsonUtil;
import core.util.StringUtil;

public class JsonUtilImpl implements JsonUtil {

    private static Gson gson = new Gson();

    @Override
    public <O> String to(O o) {
        try {
            return gson.toJson(o);
        } catch (Exception e) {
            return StringUtil.BLANK;
        }
    }

    @Override
    public <T> T from(String json, Class<T> type    ) {
        try {
            return gson.fromJson(json, type);
        } catch (Exception e) {
            return null;
        }
    }
}
