package com.example.validationframework.validator.strategy;

import com.example.validationframework.annotation.Email;
import com.example.validationframework.validator.core.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EmailStrategyTest {

    private EmailStrategy strategy;
    private Email annotation;

    @BeforeEach
    void setUp() {
        strategy = new EmailStrategy();
        annotation = new Email() {
            @Override
            public String message() { return "Field must be a valid email address"; }
            @Override
            public Class<? extends Annotation> annotationType() { return Email.class; }
        };
    }

    @Test
    void validatesNullAsInvalid() {
        ValidationResult result = strategy.validate("email", null, annotation);
        assertFalse(result.isValid());
    }

    @Test
    void validatesMalformedEmailAsInvalid() {
        ValidationResult result = strategy.validate("email", "not-an-email", annotation);
        assertFalse(result.isValid());
    }

    @Test
    void validatesMissingDomainAsInvalid() {
        ValidationResult result = strategy.validate("email", "user@", annotation);
        assertFalse(result.isValid());
    }

    @Test
    void validatesValidEmailAsValid() {
        ValidationResult result = strategy.validate("email", "user@example.com", annotation);
        assertTrue(result.isValid());
    }

    @Test
    void validatesEmailWithSubdomainAsValid() {
        ValidationResult result = strategy.validate("email", "user@mail.example.com", annotation);
        assertTrue(result.isValid());
    }
}
