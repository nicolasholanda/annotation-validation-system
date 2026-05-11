package com.example.validationframework.validator.core;

public abstract class AbstractValidator {

    public final ValidationResult validate(String fieldName, Object value) {
        preValidate(fieldName, value);
        ValidationResult result = doValidate(fieldName, value);
        postValidate(fieldName, result);
        return result;
    }

    protected void preValidate(String fieldName, Object value) {
    }

    protected abstract ValidationResult doValidate(String fieldName, Object value);

    protected void postValidate(String fieldName, ValidationResult result) {
    }
}
