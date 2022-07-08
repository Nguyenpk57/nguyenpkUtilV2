package core.module.impl;

import core.log.factory.LoggerAbstractFactory;
import core.log.factory.LoggerFactory;
import core.module.Module;
import core.util.*;
import core.util.config.LoadConfigUtil;
import core.util.config.impl.FileConfigUtilImpl;
import core.util.config.impl.PropertiesUtilImpl;
import core.util.config.impl.ResourceUtilImpl;
import core.util.format.Formatter;
import core.util.format.impl.DecimalFormatterImpl;
import core.util.format.impl.MessageFormatterImpl;
import core.util.format.impl.SimpleDateFormatterImpl;
import core.util.impl.*;
import core.util.impl.number.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class ModuleImpl implements Module {
    @Override
    public Locale locale() {
        Locale.setDefault(new Locale("es", "pe"));
        return Locale.getDefault();
    }

    @Override
    public LoggerAbstractFactory loggerFactory() {
        return LoggerFactory.getFactory();
    }

    @Override
    public DateTimeUtil dateTimeUtil() {
        return new DateTimeUtilImpl();
    }

    @Override
    public StringUtil stringUtil() {
        return new StringUtilImpl();
    }

    @Override
    public CompareUtil compareUtil() {
        return new CompareUtilImpl();
    }

    @Override
    public JsonUtil jsonUtil() {
        return new JsonUtilImpl();
    }

    @Override
    public CollectionUtil collectionUtil() {
        return new CollectionUtilImpl();
    }

    @Override
    public PatternBuilder patternBuilder() {
        return new PatternBuilderImpl();
    }

    @Override
    public ReflectUtil reflectUtil() {
        return new ReflectUtilImpl();
    }

    @Override
    public ValidationUtil validationUtil() {
        return new ValidationUtilImpl();
    }

    @Override
    public Formatter<DecimalFormat> decimalFormatter() {
        return new DecimalFormatterImpl();
    }

    @Override
    public Formatter<MessageFormat> messageFormatter() {
        return new MessageFormatterImpl();
    }

    @Override
    public Formatter<SimpleDateFormat> simpleDateFormatter() {
        return new SimpleDateFormatterImpl();
    }

    @Override
    public NumberUtil<BigDecimal> bigDecimalUtil() {
        return new BigDecimalUtilImpl();
    }

    @Override
    public NumberUtil<BigInteger> bigIntegerUtil() {
        return new BigIntegerUtilImpl();
    }

    @Override
    public NumberUtil<Double> doubleUtil() {
        return new DoubleUtilImpl();
    }

    @Override
    public NumberUtil<Float> floatUtil() {
        return new FloatUtilImpl();
    }

    @Override
    public NumberUtil<Integer> integerUtil() {
        return new IntegerUtilImpl();
    }

    @Override
    public NumberUtil<Long> longUtil() {
        return new LongUtilImpl();
    }

    @Override
    public LoadConfigUtil fileConfigUtil() {
        return new FileConfigUtilImpl();
    }

    @Override
    public LoadConfigUtil propertiesfigUtil() {
        return new PropertiesUtilImpl();
    }

    @Override
    public LoadConfigUtil resourcefigUtil() {
        return new ResourceUtilImpl();
    }

    @Override
    public <K, V> MapBuilder<K, V> mapBuilder() {
        return new MapBuilderImpl<K, V>();
    }

    @Override
    public CacheBuilder cacheBuilder() {
        return new CacheBuilderImpl();
    }


}
