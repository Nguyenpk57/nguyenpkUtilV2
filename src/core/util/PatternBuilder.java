package core.util;

import java.util.regex.Pattern;

public interface PatternBuilder {

    Pattern get(String regex);
}
