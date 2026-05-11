package com.example.validationframework.service;

import com.example.validationframework.observer.ValidationEvent;
import com.example.validationframework.observer.ValidationEventPublisher;
import com.example.validationframework.observer.ValidationListener;
import com.example.validationframework.validator.composite.FormValidator;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.factory.AnnotationValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {

    private final AnnotationValidatorFactory factory;
    private final ValidationEventPublisher publisher;

    public ValidationService(AnnotationValidatorFactory factory,
                             ValidationEventPublisher publisher,
                             List<ValidationListener> listeners) {
        this.factory = factory;
        this.publisher = publisher;
        listeners.forEach(publisher::subscribe);
    }

    public <T> ValidationResult validate(T form) {
        FormValidator formValidator = FormValidator.forClass(form.getClass(), factory);
        ValidationResult result = formValidator.validate(form);
        publisher.publish(new ValidationEvent(form.getClass().getSimpleName(), result));
        return result;
    }
}
