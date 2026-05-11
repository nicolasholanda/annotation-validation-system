package com.example.validationframework.validator.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationResult {

    private final List<ValidationError> errors = new ArrayList<>();

    public void addError(ValidationError error) {
        errors.add(error);
    }

    public void merge(ValidationResult other) {
        errors.addAll(other.errors);
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<ValidationError> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
