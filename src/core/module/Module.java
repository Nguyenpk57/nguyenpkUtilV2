package core.module;

import core.log.factory.LoggerAbstractFactory;
import core.util.*;
import core.util.config.LoadConfigUtil;
import core.util.config.impl.FileConfigUtilImpl;
import core.util.format.Formatter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public interface Module {

    Locale locale();

    LoggerAbstractFactory loggerFactory();

    DateTimeUtil dateTimeUtil();

    StringUtil stringUtil();

    CompareUtil compareUtil();

    JsonUtil jsonUtil();

    CollectionUtil collectionUtil();

    PatternBuilder patternBuilder();

    ReflectUtil reflectUtil();

    ValidationUtil validationUtil();

    Formatter<DecimalFormat> decimalFormatter();

    Formatter<MessageFormat> messageFormatter();

    Formatter<SimpleDateFormat> simpleDateFormatter();

    NumberUtil<BigDecimal> bigDecimalUtil();

    NumberUtil<BigInteger> bigIntegerUtil();

    NumberUtil<Double> doubleUtil();

    NumberUtil<Float> floatUtil();

    NumberUtil<Integer> integerUtil();

    NumberUtil<Long> longUtil();

    LoadConfigUtil fileConfigUtil();

    LoadConfigUtil propertiesfigUtil();

    LoadConfigUtil resourcefigUtil();

    <K, V> MapBuilder<K, V>  mapBuilder();

}
