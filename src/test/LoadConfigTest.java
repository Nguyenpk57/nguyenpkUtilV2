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
        //propertiesConfigUtil: properties/language/Language
        String valueDeafult = propertiesConfigUtil.getValue("language.file.path");
        System.out.println("propertiesConfigUtil: " + valueDeafult);

        //propertiesConfigUtil: properties/language/Language
        String value = propertiesConfigUtil.getValue("src/properties/config.properties", "language.file.path");
        System.out.println("propertiesConfigUtil: " + value);

        //resourceConfigUtil: [R0000] Success
        valueDeafult = resourceConfigUtil.getValue("properties/language/Language_en", "R0000");
        System.out.println("resourceConfigUtil: " + valueDeafult);

        //resourceConfigUtil: [R0000] Success
        value = resourceConfigUtil.getValue("R0000");
        System.out.println("resourceConfigUtil: " + value);

        //fileConfigUtil: properties/language/Language
        valueDeafult = fileConfigUtil.getValue("language.file.path");
        System.out.println("fileConfigUtil: " + valueDeafult);

        //fileConfigUtil: properties/language/Language
        value = fileConfigUtil.getValue("src/properties/config.properties", "language.file.path");
        System.out.println("fileConfigUtil: " + value);

    }


}
