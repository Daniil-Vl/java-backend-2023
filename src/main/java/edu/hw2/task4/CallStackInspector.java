package edu.hw2.task4;

public class CallStackInspector {

    private CallStackInspector() {
    }

    private static void deadEndMethod() throws Exception {
        throw new Exception(
            "This is exception for dead end method. You shouldn't see this message.");
    }

    /**
     * Get info about class and method, where this method was called
     *
     * @return record CallingInfo(String className, String methodName)
     */
    public static CallingInfo callingInfo() {
        CallingInfo result = null;
        try {
            deadEndMethod();
        } catch (Exception exc) {
            StackTraceElement elem = exc.getStackTrace()[2];
            result = new CallingInfo(elem.getClassName(), elem.getMethodName());
        }
        return result;
    }
}
