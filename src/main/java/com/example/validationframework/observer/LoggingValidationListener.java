package com.example.validationframework.observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggingValidationListener implements ValidationListener {

    private static final Logger logger = LoggerFactory.getLogger(LoggingValidationListener.class);

    @Override
    public void onValidationComplete(ValidationEvent event) {
        if (event.getResult().isValid()) {
            logger.info("Validation passed for {}", event.getFormClass());
        } else {
            logger.warn("Validation failed for {} with {} error(s)",
                    event.getFormClass(),
                    event.getResult().getErrors().size());
        }
    }
}
