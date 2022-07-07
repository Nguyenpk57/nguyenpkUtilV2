package core.util.format;

import java.text.Format;

public interface Formatter<F extends Format> {

    F get(String pattern);
}
