package com.example.validationframework.validator.factory;

import com.example.validationframework.validator.core.ValidationStrategy;
import com.example.validationframework.validator.strategy.EmailStrategy;
import com.example.validationframework.validator.strategy.MaxLengthStrategy;
import com.example.validationframework.validator.strategy.MaxStrategy;
import com.example.validationframework.validator.strategy.MinLengthStrategy;
import com.example.validationframework.validator.strategy.MinStrategy;
import com.example.validationframework.validator.strategy.NotBlankStrategy;
import com.example.validationframework.validator.strategy.NotNullStrategy;
import com.example.validationframework.validator.strategy.PatternStrategy;
import com.example.validationframework.validator.strategy.RangeStrategy;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class AnnotationValidatorFactory {

    private final Map<Class<? extends Annotation>, ValidationStrategy> registry = new HashMap<>();

    public AnnotationValidatorFactory() {
        register(new NotBlankStrategy());
        register(new NotNullStrategy());
        register(new MinLengthStrategy());
        register(new MaxLengthStrategy());
        register(new EmailStrategy());
        register(new PatternStrategy());
        register(new RangeStrategy());
        register(new MinStrategy());
        register(new MaxStrategy());
    }

    private void register(ValidationStrategy strategy) {
        registry.put(strategy.supportedAnnotation(), strategy);
    }

    public Optional<ValidationStrategy> getStrategy(Class<? extends Annotation> annotationType) {
        return Optional.ofNullable(registry.get(annotationType));
    }

    public boolean supports(Class<? extends Annotation> annotationType) {
        return registry.containsKey(annotationType);
    }
}
