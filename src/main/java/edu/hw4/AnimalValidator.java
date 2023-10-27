package edu.hw4;

import java.util.LinkedHashSet;
import java.util.Set;

public class AnimalValidator {

    private AnimalValidator() {
    }

    public static Set<ValidationError> validate(Animal animal) {

        // Using LinkedHashSet to keep order of elements
        Set<ValidationError> errors = new LinkedHashSet<>();

        if (animal.name().isBlank()) {
            errors.add(new ValidationError("Name cannot be blank", ValidationError.Field.name));
        }

        if (animal.type() == null) {
            errors.add(new ValidationError("Type cannot be null", ValidationError.Field.type));
        }

        if (animal.sex() == null) {
            errors.add(new ValidationError("Sex cannot be null", ValidationError.Field.sex));
        }

        if (animal.age() < 0) {
            errors.add(new ValidationError("Age must be non-negative", ValidationError.Field.age));
        }

        if (animal.height() < 0) {
            errors.add(new ValidationError("Height must be non-negative", ValidationError.Field.height));
        }

        if (animal.weight() < 0) {
            errors.add(new ValidationError("Weight must be non-negative", ValidationError.Field.weight));
        }

        return errors;
    }
}
