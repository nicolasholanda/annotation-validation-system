package com.example.validationframework.validator.strategy;

import com.example.validationframework.annotation.Max;
import com.example.validationframework.validator.core.ValidationError;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.core.ValidationStrategy;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;

public class MaxStrategy implements ValidationStrategy {

    @Override
    public ValidationResult validate(String fieldName, Object value, Annotation annotation) {
        ValidationResult result = new ValidationResult();
        Max max = (Max) annotation;
        if (value == null) {
            return result;
        }
        try {
            BigDecimal numeric = new BigDecimal(value.toString());
            BigDecimal maxValue = BigDecimal.valueOf(max.value());
            if (numeric.compareTo(maxValue) > 0) {
                String message = max.message().equals("Field value exceeds maximum")
                        ? "Field must be at most " + max.value()
                        : max.message();
                result.addError(new ValidationError(fieldName, message));
            }
        } catch (NumberFormatException e) {
            result.addError(new ValidationError(fieldName, "Field must be a number"));
        }
        return result;
    }

    @Override
    public Class<? extends Annotation> supportedAnnotation() {
        return Max.class;
    }
}
