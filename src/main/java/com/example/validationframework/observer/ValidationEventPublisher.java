package com.example.validationframework.observer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ValidationEventPublisher {

    private final List<ValidationListener> listeners = new ArrayList<>();

    public void subscribe(ValidationListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(ValidationListener listener) {
        listeners.remove(listener);
    }

    public void publish(ValidationEvent event) {
        for (ValidationListener listener : listeners) {
            listener.onValidationComplete(event);
        }
    }
}
