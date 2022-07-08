package test;

import core.result.msg.MessageUtil;

public class MessageUtilTest {
    public static void main(String[] args) {
        MessageUtilTest loadConfigTest = new MessageUtilTest();
        loadConfigTest.test();
    }

    public void test() {
        //MessageUtil: [R0000] Éxito
        String msg = MessageUtil.getInstance().getMessage("es", "R0000");
        System.out.println("MessageUtil getMessage: " + msg);

        //MessageUtil: [R0002] Success param_1/param_2
        msg = MessageUtil.getInstance().getMessage("en", "R0002", "param_1", "param_2");
        System.out.println("MessageUtil getMessage: " + msg);

        //MessageUtil getMessageByPath: [R0000] Éxito
        msg = MessageUtil.getInstance().getMessageByPath("properties/language/Language", "es", "R0000");
        System.out.println("MessageUtil getMessageByPath: " + msg);

        //MessageUtil getMessageByPath: [R0002] Éxito param_1/param_2
        msg = MessageUtil.getInstance().getMessageByPath("properties/language/Language", "es", "R0002", "param_1", "param_2");
        System.out.println("MessageUtil getMessageByPath: " + msg);

    }
}
