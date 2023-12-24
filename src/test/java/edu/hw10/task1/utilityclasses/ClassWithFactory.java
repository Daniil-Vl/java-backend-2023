package edu.hw10.task1.utilityclasses;

public class ClassWithFactory {
    private final String stringParam;
    private final int intParam;

    private ClassWithFactory(String stringParam, int intParam) {
        this.stringParam = stringParam;
        this.intParam = intParam;
    }

    public static ClassWithFactory create(String stringParam, int intParam) {
        return new ClassWithFactory(stringParam, intParam);
    }

    @Override public String toString() {
        return "ClassWithFactory{"
            + "stringParam='" + stringParam + '\''
            + ", intParam=" + intParam
            + '}';
    }
}
