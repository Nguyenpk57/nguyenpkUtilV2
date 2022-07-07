package core.util.format.impl;

import core.util.format.Formatter;

import java.text.MessageFormat;

public class MessageFormatterImpl implements Formatter<MessageFormat> {

    @Override
    public MessageFormat get(String pattern) {
        MessageFormat f = new MessageFormat(pattern);
        return f;
    }
}
