package com.example.validationframework.validator.chain;

import com.example.validationframework.validator.core.AbstractValidator;
import com.example.validationframework.validator.core.ValidationResult;

public class ChainedValidator extends AbstractValidator {

    private final ValidatorNode head;

    public ChainedValidator(ValidatorNode head) {
        this.head = head;
    }

    @Override
    protected ValidationResult doValidate(String fieldName, Object value) {
        if (head == null) {
            return new ValidationResult();
        }
        return head.handle(fieldName, value);
    }
}
