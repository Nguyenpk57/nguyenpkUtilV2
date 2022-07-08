package core.util.config.impl;

import core.log.Logger;
import core.module.ModuleFactory;
import core.util.config.LoadConfigUtil;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FileConfigUtilImpl implements LoadConfigUtil    {

    private final Logger logger = ModuleFactory.getFactory().loggerFactory().getLogger(this.getClass());

    @Override
    public String getValue(String key) {
        String path = CONFIG_DEFAULT.DEFAULT_PROPERTIES;
        return getValue(path, key);
    }

    @Override
    public String getValue(String fileName, String key) {
        return getValue(getConfigs(fileName), key);
    }

    private String getValue(HashMap values, String key) {
        if (values == null) {
            return null;
        }
        Object value = values.get(key);
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return StringUtils.trim((String) value);
        }
        if (value instanceof List) {
            List<String> lst = (List<String>) value;
            for (String v : lst) {
                v = StringUtils.trim((String) v);
                if (!StringUtils.isEmpty(v)) {
                    return v;
                }
            }
            return null;
        }
        return null;
    }

    private HashMap getConfigs(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        fileName = fileName.trim();
        HashMap config = load(fileName);
        return config;
    }

    private HashMap load(String fileName) {
        List<String> lines = read(fileName);
        if (lines == null) {
            return null;
        }
        HashMap result = new HashMap();
        String joiner = "=";
        String comment = "#";
        List<String> keys = new ArrayList<String>();
        for (String line : lines) {
            line = StringUtils.trim(line);
            if (line == null || line.startsWith(comment)) {
                continue;
            }
            String[] arr = line.split(joiner, 2);
            if (arr == null || arr.length != 2) {
                continue;
            }
            String key = StringUtils.trim(arr[0]);
            if (StringUtils.isEmpty(key)) {
                continue;
            }
            String value = StringUtils.trim(arr[1]);
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            if (keys.contains(key)) {
                Object v = result.get(key);
                if (v == null) {
                    result.put(key, value);
                    continue;
                }
                if (v instanceof String) {
                    String vStr = StringUtils.trim((String) v);
                    if (value.equals(vStr)) {
                        continue;
                    }
                    List<String> values = new ArrayList<String>();
                    values.add(vStr);
                    values.add(value);
                    result.put(key, values);
                    continue;
                }
                if (!(v instanceof List)) {
                    continue;
                }
                List<String> values = (List<String>) v;
                if (!values.contains(value)) {
                    values.add(value);
                }
                result.put(key, values);
                continue;
            }
            result.put(key, value);
            keys.add(key);
        }
        return result;
    }

    public List<String> read(String filePath) {
        List<String> result = new ArrayList();
        BufferedReader br = null;
        try {
            try {
                br = new BufferedReader(new FileReader(filePath));
            } catch (Exception ex) {
                logger.error("read: " + "filePath: " + filePath + " BufferedReader Exception: " + ex.getMessage());
            }
            if (br == null) {
                br = new BufferedReader(new FileReader(new File(filePath).getCanonicalPath()));
            }
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    result.add(line);
                }
            }
        } catch (Throwable ex) {
            logger.error("read: " + "filePath: " + filePath + " Exception: " + ex.getMessage());
        } finally {
            closes(br);
        }
        return result;
    }

    public void closes(Object... objs) {
        if (objs != null) {
            for (Object obj : objs) {
                close(obj);
            }
        }
    }

    private Object close(Object obj) {
        try {
            if (obj == null) {
                return obj;
            }
            if (obj instanceof FileInputStream) {
                ((FileInputStream) obj).close();
            }
            if (obj instanceof InputStream) {
                ((InputStream) obj).close();
            }
            if (obj instanceof OutputStream) {
                ((OutputStream) obj).close();
            }
            if (obj instanceof ByteArrayOutputStream) {
                ((ByteArrayOutputStream) obj).close();
            }
            if (obj instanceof BufferedReader) {
                ((BufferedReader) obj).close();
            }
            if (obj instanceof FileWriter) {
                ((FileWriter) obj).close();
            }
        } catch (Throwable ex) {
            logger.error("close: " + ex.getMessage());
        } finally {
            obj = null;
        }
        return obj;
    }
}
