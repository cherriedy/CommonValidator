package com.optlab.validator.validators;

import com.optlab.validator.annotations.SpecialChar;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;

import java.lang.annotation.Annotation;

/** Validator that checks if the input contains at least one special characters. */
public class SpecialCharValidator extends PatternValidator implements AnnotationValidator<String> {
    /** A regex pattern that matches any string containing at least one special character. */
    private static final String PATTERN = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*";

    public SpecialCharValidator(int errorMessageResId) {
        super(PATTERN, errorMessageResId);
    }

    @Override
    public void initialize(Annotation annotation) {
        super.errorMessageResId = ((SpecialChar) annotation).errorMessageResId();
    }

    @Override
    public ValidationResult verify(String input) {
        return validate(input);
    }
}
