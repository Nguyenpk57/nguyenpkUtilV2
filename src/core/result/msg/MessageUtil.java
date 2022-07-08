package core.result.msg;

import core.log.Logger;
import core.module.ModuleFactory;
import core.util.StringUtil;
import core.util.config.LoadConfigUtil;
import core.util.format.Formatter;

import java.text.MessageFormat;
import java.util.HashMap;

public class MessageUtil {

    private static final Object LOCK = new Object();
    private static MessageUtil instance;
    private static HashMap<String, MessageUtil> instances;
    private String localeSeparator;
    private final LoadConfigUtil propertiesConfigUtil = ModuleFactory.getFactory().propertiesfigUtil();
    private final LoadConfigUtil resourceConfigUtil = ModuleFactory.getFactory().resourcefigUtil();
    private final Formatter<MessageFormat> messageFormatFormatter = ModuleFactory.getFactory().messageFormatter();
    private final StringUtil stringUtil = ModuleFactory.getFactory().stringUtil();
    private final Logger logger = ModuleFactory.getFactory().loggerFactory().getLogger(this.getClass());

    private MessageUtil() {
        initialization();
    }

    public static MessageUtil getInstance() {
        synchronized (LOCK) {
            if (instance == null) {
                instance = new MessageUtil();
            }
            return instance;
        }
    }

    private void initialization() {
        localeSeparator = "_";
    }

    //<editor-fold defaultstate="collapsed" desc="getMessage">
    public String getMessage(String language, String key) {
        try {
            String languageFilePath = getLanguageFile(language);
            return resourceConfigUtil.getValue(languageFilePath, key);
        } catch (Throwable ex) {
            logger.error(ex.getMessage());
            return key;
        }
    }

    public String getMessage(String language, String key, Object... args) {
        try {
            return messageFormatFormatter.get(getMessage(language, key)).format(buildArgs(language, args));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return key;
        }
    }

    private String getLanguageFile(String language) {
        return new StringBuilder().append(propertiesConfigUtil.getValue("language.file.path"))
                .append(localeSeparator)
                .append(stringUtil.trim(language)).toString();
    }

    private Object[] buildArgs(String language, Object... args) {
        Object[] result = null;
        if (args == null) {
            return result;
        }
        int length = args.length;
        if (length == 0) {
            return result;
        }
        result = new Object[length];
        int i = 0;
        for (Object arg : args) {
            if (arg == null) {
                result[i++] = null;
                continue;
            }
            if (arg instanceof MessageArgument) {
                result[i++] = ((MessageArgument) arg).getMessage(language);
                continue;
            }
            result[i++] = arg;
        }
        return result;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="getMessage by path">
    public String getMessageByPath(String path, String language, String key) {
        try {
            String languageFilePath = getLanguageFileByPath(path, language);
            return resourceConfigUtil.getValue(languageFilePath, key);
        } catch (Throwable ex) {
            logger.error(ex.getMessage());
            return key;
        }
    }

    public String getMessageByPath(String path, String language, String key, Object... args) {
        try {
            return messageFormatFormatter.get(getMessage(language, key)).format(buildArgs(language, args));
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            return key;
        }
    }

    private String getLanguageFileByPath(String path, String language) {
        return new StringBuilder().append(path)
                .append(localeSeparator)
                .append(stringUtil.trim(language)).toString();
    }
    //</editor-fold>

}
