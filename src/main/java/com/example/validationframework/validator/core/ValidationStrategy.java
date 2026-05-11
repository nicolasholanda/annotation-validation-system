package com.example.validationframework.validator.core;

import java.lang.annotation.Annotation;

public interface ValidationStrategy {

    ValidationResult validate(String fieldName, Object value, Annotation annotation);

    Class<? extends Annotation> supportedAnnotation();
}
