package edu.hw2.task4;

public class CallStackInspector {

    private CallStackInspector() {
    }

    /**
     * Get info about class and method, where this method was called
     *
     * @return record CallingInfo(String className, String methodName)
     */
    public static CallingInfo callingInfo() {
        Thread thread = Thread.currentThread();
        StackTraceElement elem = thread.getStackTrace()[2];
        return new CallingInfo(elem.getClassName(), elem.getMethodName());
    }
}
