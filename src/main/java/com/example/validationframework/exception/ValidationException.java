package com.example.validationframework.exception;

import com.example.validationframework.validator.core.ValidationResult;

public class ValidationException extends RuntimeException {

    private final ValidationResult result;

    public ValidationException(ValidationResult result) {
        super("Validation failed with " + result.getErrors().size() + " error(s)");
        this.result = result;
    }

    public ValidationResult getResult() {
        return result;
    }
}
