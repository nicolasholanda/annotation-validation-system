package com.example.validationframework.validator.chain;

import com.example.validationframework.validator.core.ValidationStrategy;
import com.example.validationframework.validator.factory.AnnotationValidatorFactory;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

public class ValidationChainBuilder {

    private final AnnotationValidatorFactory factory;
    private final List<ValidatorNode> nodes = new ArrayList<>();

    public ValidationChainBuilder(AnnotationValidatorFactory factory) {
        this.factory = factory;
    }

    public ValidationChainBuilder addAnnotation(Annotation annotation) {
        factory.getStrategy(annotation.annotationType())
                .ifPresent(strategy -> nodes.add(new ValidatorNode(strategy, annotation)));
        return this;
    }

    public ChainedValidator build() {
        if (nodes.isEmpty()) {
            return new ChainedValidator(null);
        }
        for (int i = 0; i < nodes.size() - 1; i++) {
            nodes.get(i).setNext(nodes.get(i + 1));
        }
        return new ChainedValidator(nodes.get(0));
    }
}
