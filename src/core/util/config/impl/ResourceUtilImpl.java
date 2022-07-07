package core.util.config.impl;

import core.module.ModuleFactory;
import core.util.StringUtil;
import core.util.config.LoadConfigUtil;

import java.util.ResourceBundle;

public class ResourceUtilImpl implements LoadConfigUtil {
    private final StringUtil stringUtil = ModuleFactory.getFactory().stringUtil();

    @Override
    public String getValue(String key) {
        return getValue(CONFIG_DEFAULT.DEFAULT_BUNDLE);
    }

    @Override
    public String getValue(String fileName, String key) {
        return getBundle(fileName).getString(key);
    }

    public ResourceBundle getBundle(String fileName) {
        if (stringUtil.isEmpty(fileName)) {
            return null;
        }
        fileName = stringUtil.trim(fileName);
        return ResourceBundle.getBundle(fileName);
    }
}
