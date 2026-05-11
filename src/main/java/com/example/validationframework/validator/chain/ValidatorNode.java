package com.example.validationframework.validator.chain;

import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.core.ValidationStrategy;

import java.lang.annotation.Annotation;

public class ValidatorNode {

    private final ValidationStrategy strategy;
    private final Annotation annotation;
    private ValidatorNode next;

    public ValidatorNode(ValidationStrategy strategy, Annotation annotation) {
        this.strategy = strategy;
        this.annotation = annotation;
    }

    public void setNext(ValidatorNode next) {
        this.next = next;
    }

    public ValidationResult handle(String fieldName, Object value) {
        ValidationResult result = strategy.validate(fieldName, value, annotation);
        if (next != null) {
            result.merge(next.handle(fieldName, value));
        }
        return result;
    }
}
