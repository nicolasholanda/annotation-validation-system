package com.example.validationframework.validator.strategy;

import com.example.validationframework.annotation.Min;
import com.example.validationframework.validator.core.ValidationError;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.core.ValidationStrategy;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;

public class MinStrategy implements ValidationStrategy {

    @Override
    public ValidationResult validate(String fieldName, Object value, Annotation annotation) {
        ValidationResult result = new ValidationResult();
        Min min = (Min) annotation;
        if (value == null) {
            return result;
        }
        try {
            BigDecimal numeric = new BigDecimal(value.toString());
            BigDecimal minValue = BigDecimal.valueOf(min.value());
            if (numeric.compareTo(minValue) < 0) {
                String message = min.message().equals("Field value is below minimum")
                        ? "Field must be at least " + min.value()
                        : min.message();
                result.addError(new ValidationError(fieldName, message));
            }
        } catch (NumberFormatException e) {
            result.addError(new ValidationError(fieldName, "Field must be a number"));
        }
        return result;
    }

    @Override
    public Class<? extends Annotation> supportedAnnotation() {
        return Min.class;
    }
}
