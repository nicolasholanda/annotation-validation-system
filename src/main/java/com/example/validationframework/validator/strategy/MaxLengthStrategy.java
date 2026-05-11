package com.example.validationframework.validator.strategy;

import com.example.validationframework.annotation.MaxLength;
import com.example.validationframework.validator.core.ValidationError;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.core.ValidationStrategy;

import java.lang.annotation.Annotation;

public class MaxLengthStrategy implements ValidationStrategy {

    @Override
    public ValidationResult validate(String fieldName, Object value, Annotation annotation) {
        ValidationResult result = new ValidationResult();
        MaxLength maxLength = (MaxLength) annotation;
        if (value != null && value.toString().length() > maxLength.value()) {
            String message = maxLength.message().equals("Field is too long")
                    ? "Field must have at most " + maxLength.value() + " characters"
                    : maxLength.message();
            result.addError(new ValidationError(fieldName, message));
        }
        return result;
    }

    @Override
    public Class<? extends Annotation> supportedAnnotation() {
        return MaxLength.class;
    }
}
