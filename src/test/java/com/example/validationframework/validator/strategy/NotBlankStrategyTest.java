package com.example.validationframework.validator.strategy;

import com.example.validationframework.annotation.NotBlank;
import com.example.validationframework.validator.core.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NotBlankStrategyTest {

    private NotBlankStrategy strategy;
    private NotBlank annotation;

    @BeforeEach
    void setUp() {
        strategy = new NotBlankStrategy();
        annotation = new NotBlank() {
            @Override
            public String message() { return "Field must not be blank"; }
            @Override
            public Class<? extends Annotation> annotationType() { return NotBlank.class; }
        };
    }

    @Test
    void validatesNullAsInvalid() {
        ValidationResult result = strategy.validate("field", null, annotation);
        assertFalse(result.isValid());
    }

    @Test
    void validatesEmptyStringAsInvalid() {
        ValidationResult result = strategy.validate("field", "", annotation);
        assertFalse(result.isValid());
    }

    @Test
    void validatesBlankStringAsInvalid() {
        ValidationResult result = strategy.validate("field", "   ", annotation);
        assertFalse(result.isValid());
    }

    @Test
    void validatesNonBlankStringAsValid() {
        ValidationResult result = strategy.validate("field", "hello", annotation);
        assertTrue(result.isValid());
    }
}
