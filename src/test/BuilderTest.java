package test;

import core.module.ModuleFactory;
import core.util.JsonUtil;
import test.bean.Entity;

public class BuilderTest {
    private static JsonUtil jsonUtil = ModuleFactory.getFactory().jsonUtil();

    public static void main(String[] args) throws Exception {
        Entity entity = Entity.builder()
                .id(1L)
                .name("Nguyenpk")
                .code("C6972459")
                .isdn("929394935")
                .build();

        //Entity: {"id":1,"name":"Nguyenpk","code":"C6972459"}
        System.out.println("Entity: " + jsonUtil.to(entity));
    }
}
