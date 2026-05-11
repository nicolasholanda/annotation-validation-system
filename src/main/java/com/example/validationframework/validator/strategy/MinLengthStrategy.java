package com.example.validationframework.validator.strategy;

import com.example.validationframework.annotation.MinLength;
import com.example.validationframework.validator.core.ValidationError;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.core.ValidationStrategy;

import java.lang.annotation.Annotation;

public class MinLengthStrategy implements ValidationStrategy {

    @Override
    public ValidationResult validate(String fieldName, Object value, Annotation annotation) {
        ValidationResult result = new ValidationResult();
        MinLength minLength = (MinLength) annotation;
        if (value == null || value.toString().length() < minLength.value()) {
            String message = minLength.message().equals("Field is too short")
                    ? "Field must have at least " + minLength.value() + " characters"
                    : minLength.message();
            result.addError(new ValidationError(fieldName, message));
        }
        return result;
    }

    @Override
    public Class<? extends Annotation> supportedAnnotation() {
        return MinLength.class;
    }
}
