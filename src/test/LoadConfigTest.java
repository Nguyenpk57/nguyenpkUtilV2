package test;

import core.module.ModuleFactory;
import core.util.config.LoadConfigUtil;

public class LoadConfigTest {
    private final LoadConfigUtil propertiesConfigUtil = ModuleFactory.getFactory().propertiesfigUtil();
    private final LoadConfigUtil resourceConfigUtil = ModuleFactory.getFactory().resourcefigUtil();
    private final LoadConfigUtil fileConfigUtil = ModuleFactory.getFactory().fileConfigUtil();

    public static void main(String[] args) {
        LoadConfigTest loadConfigTest = new LoadConfigTest();
        loadConfigTest.test();
    }

    public void test() {
        String valueDeafult = propertiesConfigUtil.getValue("language.file.path");
        System.out.println("propertiesConfigUtil: " + valueDeafult);

        String value = propertiesConfigUtil.getValue("src/properties/config.properties", "language.file.path");
        System.out.println("propertiesConfigUtil: " + value);

        valueDeafult = resourceConfigUtil.getValue("properties/language/Language_en", "R0000");
        System.out.println("resourceConfigUtil: " + valueDeafult);

        value = resourceConfigUtil.getValue("R0000");
        System.out.println("resourceConfigUtil: " + value);

        valueDeafult = fileConfigUtil.getValue("language.file.path");
        System.out.println("fileConfigUtil: " + valueDeafult);

        value = fileConfigUtil.getValue("src/properties/config.properties", "language.file.path");
        System.out.println("fileConfigUtil: " + value);

    }


}
