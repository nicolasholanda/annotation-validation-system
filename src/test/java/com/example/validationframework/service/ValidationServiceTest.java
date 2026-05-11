package com.example.validationframework.service;

import com.example.validationframework.annotation.Email;
import com.example.validationframework.annotation.NotBlank;
import com.example.validationframework.observer.ValidationEventPublisher;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.factory.AnnotationValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationServiceTest {

    private ValidationService validationService;

    @BeforeEach
    void setUp() {
        validationService = new ValidationService(
                new AnnotationValidatorFactory(),
                new ValidationEventPublisher(),
                Collections.emptyList()
        );
    }

    @Test
    void validatesFormWithNoAnnotationsAsValid() {
        Object emptyForm = new Object();
        ValidationResult result = validationService.validate(emptyForm);
        assertTrue(result.isValid());
    }

    @Test
    void detectsInvalidEmailOnAnnotatedForm() {
        class TestForm {
            @NotBlank
            @Email
            String email = "not-an-email";
        }
        ValidationResult result = validationService.validate(new TestForm());
        assertFalse(result.isValid());
        assertTrue(result.getErrors().stream().anyMatch(e -> e.getField().equals("email")));
    }

    @Test
    void passesWhenAnnotatedFormIsValid() {
        class TestForm {
            @NotBlank
            @Email
            String email = "valid@example.com";
        }
        ValidationResult result = validationService.validate(new TestForm());
        assertTrue(result.isValid());
    }
}
