package com.example.validationframework.validator.factory;

import com.example.validationframework.annotation.Email;
import com.example.validationframework.annotation.MaxLength;
import com.example.validationframework.annotation.MinLength;
import com.example.validationframework.annotation.NotBlank;
import com.example.validationframework.annotation.NotNull;
import com.example.validationframework.annotation.Pattern;
import com.example.validationframework.annotation.Range;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AnnotationValidatorFactoryTest {

    private AnnotationValidatorFactory factory;

    @BeforeEach
    void setUp() {
        factory = new AnnotationValidatorFactory();
    }

    @Test
    void supportsNotBlank() {
        assertTrue(factory.supports(NotBlank.class));
    }

    @Test
    void supportsNotNull() {
        assertTrue(factory.supports(NotNull.class));
    }

    @Test
    void supportsMinLength() {
        assertTrue(factory.supports(MinLength.class));
    }

    @Test
    void supportsMaxLength() {
        assertTrue(factory.supports(MaxLength.class));
    }

    @Test
    void supportsEmail() {
        assertTrue(factory.supports(Email.class));
    }

    @Test
    void supportsPattern() {
        assertTrue(factory.supports(Pattern.class));
    }

    @Test
    void supportsRange() {
        assertTrue(factory.supports(Range.class));
    }

    @Test
    void doesNotSupportArbitraryAnnotation() {
        assertFalse(factory.supports(Override.class));
    }

    @Test
    void getStrategyReturnsEmptyForUnknownAnnotation() {
        assertTrue(factory.getStrategy(Override.class).isEmpty());
    }

    @Test
    void getStrategyReturnsPresentForKnownAnnotation() {
        assertTrue(factory.getStrategy(Email.class).isPresent());
    }
}
