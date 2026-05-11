package com.example.validationframework.validator.strategy;

import com.example.validationframework.annotation.NotBlank;
import com.example.validationframework.validator.core.ValidationError;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.core.ValidationStrategy;

import java.lang.annotation.Annotation;

public class NotBlankStrategy implements ValidationStrategy {

    @Override
    public ValidationResult validate(String fieldName, Object value, Annotation annotation) {
        ValidationResult result = new ValidationResult();
        NotBlank notBlank = (NotBlank) annotation;
        if (value == null || value.toString().isBlank()) {
            result.addError(new ValidationError(fieldName, notBlank.message()));
        }
        return result;
    }

    @Override
    public Class<? extends Annotation> supportedAnnotation() {
        return NotBlank.class;
    }
}
