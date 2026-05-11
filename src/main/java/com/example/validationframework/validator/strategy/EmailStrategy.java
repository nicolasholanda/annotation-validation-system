package com.example.validationframework.validator.strategy;

import com.example.validationframework.annotation.Email;
import com.example.validationframework.validator.core.ValidationError;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.core.ValidationStrategy;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

public class EmailStrategy implements ValidationStrategy {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[a-zA-Z0-9._%+\\-]+@[a-zA-Z0-9.\\-]+\\.[a-zA-Z]{2,}$");

    @Override
    public ValidationResult validate(String fieldName, Object value, Annotation annotation) {
        ValidationResult result = new ValidationResult();
        Email email = (Email) annotation;
        if (value == null || !EMAIL_PATTERN.matcher(value.toString()).matches()) {
            result.addError(new ValidationError(fieldName, email.message()));
        }
        return result;
    }

    @Override
    public Class<? extends Annotation> supportedAnnotation() {
        return Email.class;
    }
}
