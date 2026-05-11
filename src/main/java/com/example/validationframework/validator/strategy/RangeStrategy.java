package com.example.validationframework.validator.strategy;

import com.example.validationframework.annotation.Range;
import com.example.validationframework.validator.core.ValidationError;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.core.ValidationStrategy;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;

public class RangeStrategy implements ValidationStrategy {

    @Override
    public ValidationResult validate(String fieldName, Object value, Annotation annotation) {
        ValidationResult result = new ValidationResult();
        Range range = (Range) annotation;
        if (value == null) {
            return result;
        }
        try {
            BigDecimal numeric = new BigDecimal(value.toString());
            BigDecimal min = BigDecimal.valueOf(range.min());
            BigDecimal max = BigDecimal.valueOf(range.max());
            if (numeric.compareTo(min) < 0 || numeric.compareTo(max) > 0) {
                String message = range.message().equals("Field value is out of range")
                        ? "Field must be between " + range.min() + " and " + range.max()
                        : range.message();
                result.addError(new ValidationError(fieldName, message));
            }
        } catch (NumberFormatException e) {
            result.addError(new ValidationError(fieldName, "Field must be a number"));
        }
        return result;
    }

    @Override
    public Class<? extends Annotation> supportedAnnotation() {
        return Range.class;
    }
}
