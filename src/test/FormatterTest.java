package test;

import core.module.ModuleFactory;
import core.util.DateTimeUtil;
import core.util.format.Formatter;

import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatterTest {
    private final Formatter<DecimalFormat> decimalFormatter = ModuleFactory.getFactory().decimalFormatter();
    private final Formatter<MessageFormat> messageFormatter = ModuleFactory.getFactory().messageFormatter();
    private final Formatter<SimpleDateFormat> simpleDateFormatter = ModuleFactory.getFactory().simpleDateFormatter();

    public static void main(String[] args) {
        FormatterTest formatterTest = new FormatterTest();
        formatterTest.test();
    }

    public void test() {
        //decimalFormatter #.##: 103921.12
        Double vld = 103921.12345D;
        String vldFormatter = decimalFormatter.get("#.##").format(vld);
        System.out.println("decimalFormatter #.##: " + vldFormatter);

        //messageFormatter {0}/{1}: Success value1/value2
        String vls = "Success {0}/{1}";
        String vlsFormatter = messageFormatter.get("#.##").format(vls, "value1", "value2");
        System.out.println("messageFormatter {0}/{1}: " + vlsFormatter);

        //simpleDateFormatter dd/MM/yyyy: 08/07/2022
        Date date = new Date();
        String dateFormatter = simpleDateFormatter.get(DateTimeUtil.DateTimePattern.DDMMYYYY).format(date);
        System.out.println("simpleDateFormatter dd/MM/yyyy: " + dateFormatter);
    }
}
