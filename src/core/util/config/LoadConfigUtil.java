package core.util.config;

import java.text.Format;

public interface LoadConfigUtil <C extends Class>{

    interface CONFIG_DEFAULT {
        String DEFAULT_BUNDLE = "properties/language/Language_en";
        String DEFAULT_PROPERTIES = "nguyenpkUtilV2/src/core/util/properties/config.properties";
    }

    String getValue(String key);

    String getValue(String fileName, String key);
}
