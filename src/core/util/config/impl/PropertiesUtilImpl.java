package core.util.config.impl;

import core.module.ModuleFactory;
import core.util.StringUtil;
import core.util.config.LoadConfigUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtilImpl implements LoadConfigUtil {

    private final StringUtil stringUtil = ModuleFactory.getFactory().stringUtil();

    @Override
    public String getValue(String key) {
        return getValue(CONFIG_DEFAULT.DEFAULT_PROPERTIES);
    }

    @Override
    public String getValue(String fileName, String key) {
        return getProperties(fileName).getProperty(key);
    }

    public Properties getProperties(String fileName) {
        if (stringUtil.isEmpty(fileName)) {
            return null;
        }
        fileName = stringUtil.trim(fileName);
        Properties pro;
        try {
            pro = new Properties();
            pro.load(new FileInputStream(fileName));
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            pro = null;
        }
        return pro;
    }
}
