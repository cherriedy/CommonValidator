package com.optlab.validator.validators;

import com.optlab.validator.annotations.Uppercase;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;

import java.lang.annotation.Annotation;

/** Validator that checks if the input contains at least one uppercase letter. */
public class UppercaseValidator extends PatternValidator implements AnnotationValidator<String> {
    /** A regex pattern that checks if the input contains at least one uppercase letter. */
    private static final String PATTERN = ".*[A-Z].*";

    public UppercaseValidator(int errorMessageResId) {
        super(PATTERN, errorMessageResId);
    }

    @Override
    public void initialize(Annotation annotation) {
        super.errorMessageResId = ((Uppercase) annotation).errorMessageResId();
    }

    @Override
    public ValidationResult verify(String input) {
        return validate(input);
    }
}
