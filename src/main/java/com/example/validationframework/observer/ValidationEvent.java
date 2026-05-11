package com.example.validationframework.observer;

import com.example.validationframework.validator.core.ValidationResult;

public class ValidationEvent {

    private final String formClass;
    private final ValidationResult result;

    public ValidationEvent(String formClass, ValidationResult result) {
        this.formClass = formClass;
        this.result = result;
    }

    public String getFormClass() {
        return formClass;
    }

    public ValidationResult getResult() {
        return result;
    }
}
