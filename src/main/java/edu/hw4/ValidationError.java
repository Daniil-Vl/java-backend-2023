package edu.hw4;

public record ValidationError(
    String errorMessage,
    Field field
) {
    enum Field {
        name, type, sex, age, height, weight
    }

    @Override
    public String toString() {
        return "ValidationError{"
            + "field=" + field
            + ", errorMessage='" + errorMessage + '\''
            + '}';
    }
}
