package com.example.validationframework.validator.composite;

import com.example.validationframework.validator.chain.ValidationChainBuilder;
import com.example.validationframework.validator.core.ValidationResult;
import com.example.validationframework.validator.factory.AnnotationValidatorFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FormValidator {

    private final List<FieldValidatorGroup> fieldGroups = new ArrayList<>();

    public static FormValidator forClass(Class<?> formClass, AnnotationValidatorFactory factory) {
        FormValidator formValidator = new FormValidator();
        for (Field field : formClass.getDeclaredFields()) {
            ValidationChainBuilder builder = new ValidationChainBuilder(factory);
            for (Annotation annotation : field.getDeclaredAnnotations()) {
                if (factory.supports(annotation.annotationType())) {
                    builder.addAnnotation(annotation);
                }
            }
            formValidator.fieldGroups.add(new FieldValidatorGroup(field.getName(), builder.build()));
        }
        return formValidator;
    }

    public ValidationResult validate(Object form) {
        ValidationResult combined = new ValidationResult();
        for (FieldValidatorGroup group : fieldGroups) {
            try {
                Field field = form.getClass().getDeclaredField(group.getFieldName());
                field.setAccessible(true);
                Object value = field.get(form);
                combined.merge(group.validate(value));
            } catch (NoSuchFieldException | IllegalAccessException ignored) {
            }
        }
        return combined;
    }
}
