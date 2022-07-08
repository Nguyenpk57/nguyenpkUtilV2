package core.result.msg;

public class MessageArgument {

    /**
     * true: in file Language; false: String message
     */
    protected boolean isMessage;
    /**
     * is String or Object MessageArgument
     */
    private Object message;
    private Object[] arguments;

    public MessageArgument(boolean isMessage, Object message, Object... arguments) {
        this.isMessage = isMessage;
        this.message = message;
        this.arguments = arguments;
    }

    public Object getMessage(String language) {
        if (isMessage) {
            return MessageUtil.getInstance().getMessage(language, (String) message, arguments);
        }
        return message;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object[] getArguments() {
        return arguments;
    }

    public void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }
}
