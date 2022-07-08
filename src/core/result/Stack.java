package core.result;

import org.apache.commons.lang3.StringUtils;

public class Stack {

    private String className;
    private String methodName;
    private Integer lineNumber;

    public Stack() {
        StackTraceElement stack = Thread.currentThread().getStackTrace()[3];
        className = stack.getClassName();
        methodName = stack.getMethodName();
        lineNumber = stack.getLineNumber();
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String catchInfo() {
        return StringUtils.join(className, ".", methodName, ":", String.valueOf(lineNumber));
    }
}
