package com.example.validationframework.validator.composite;

import com.example.validationframework.validator.chain.ChainedValidator;
import com.example.validationframework.validator.core.ValidationResult;

public class FieldValidatorGroup {

    private final String fieldName;
    private final ChainedValidator chainedValidator;

    public FieldValidatorGroup(String fieldName, ChainedValidator chainedValidator) {
        this.fieldName = fieldName;
        this.chainedValidator = chainedValidator;
    }

    public ValidationResult validate(Object value) {
        return chainedValidator.validate(fieldName, value);
    }

    public String getFieldName() {
        return fieldName;
    }
}
