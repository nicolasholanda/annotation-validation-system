package com.example.validationframework.validator.chain;

import com.example.validationframework.annotation.MinLength;
import com.example.validationframework.annotation.NotBlank;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.factory.AnnotationValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ChainedValidatorTest {

    private AnnotationValidatorFactory factory;

    @BeforeEach
    void setUp() {
        factory = new AnnotationValidatorFactory();
    }

    @Test
    void emptyChainPassesValidation() {
        ChainedValidator validator = new ValidationChainBuilder(factory).build();
        ValidationResult result = validator.validate("field", "anything");
        assertTrue(result.isValid());
    }

    @Test
    void chainCollectsAllErrors() {
        NotBlank notBlank = new NotBlank() {
            @Override public String message() { return "must not be blank"; }
            @Override public Class<? extends Annotation> annotationType() { return NotBlank.class; }
        };
        MinLength minLength = new MinLength() {
            @Override public int value() { return 10; }
            @Override public String message() { return "Field is too short"; }
            @Override public Class<? extends Annotation> annotationType() { return MinLength.class; }
        };
        ChainedValidator validator = new ValidationChainBuilder(factory)
                .addAnnotation(notBlank)
                .addAnnotation(minLength)
                .build();
        ValidationResult result = validator.validate("field", "hi");
        assertFalse(result.isValid());
        assertEquals(1, result.getErrors().size());
    }

    @Test
    void chainPassesWhenAllConstraintsMet() {
        NotBlank notBlank = new NotBlank() {
            @Override public String message() { return "must not be blank"; }
            @Override public Class<? extends Annotation> annotationType() { return NotBlank.class; }
        };
        MinLength minLength = new MinLength() {
            @Override public int value() { return 3; }
            @Override public String message() { return "Field is too short"; }
            @Override public Class<? extends Annotation> annotationType() { return MinLength.class; }
        };
        ChainedValidator validator = new ValidationChainBuilder(factory)
                .addAnnotation(notBlank)
                .addAnnotation(minLength)
                .build();
        ValidationResult result = validator.validate("field", "hello");
        assertTrue(result.isValid());
    }
}
