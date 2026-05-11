package com.example.validationframework.validator.strategy;

import com.example.validationframework.annotation.NotNull;
import com.example.validationframework.validator.core.ValidationError;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.core.ValidationStrategy;

import java.lang.annotation.Annotation;

public class NotNullStrategy implements ValidationStrategy {

    @Override
    public ValidationResult validate(String fieldName, Object value, Annotation annotation) {
        ValidationResult result = new ValidationResult();
        NotNull notNull = (NotNull) annotation;
        if (value == null) {
            result.addError(new ValidationError(fieldName, notNull.message()));
        }
        return result;
    }

    @Override
    public Class<? extends Annotation> supportedAnnotation() {
        return NotNull.class;
    }
}
