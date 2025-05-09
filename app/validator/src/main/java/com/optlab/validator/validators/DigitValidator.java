package com.optlab.validator.validators;

import com.optlab.validator.annotations.Digit;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;

import java.lang.annotation.Annotation;

/** Validator that checks if the input contains at least one digit (0-9). */
public class DigitValidator extends PatternValidator implements AnnotationValidator<String> {
    /** A regex pattern that matches a single digit (0-9). */
    private static final String PATTERN = "[0-9]";

    public DigitValidator(int errorMessageResId) {
        super(PATTERN, errorMessageResId);
    }

    @Override
    public void initialize(Annotation annotation) {
        super.errorMessageResId = ((Digit) annotation).errorMessageResId();
    }

    @Override
    public ValidationResult verify(String input) {
        return validate(input);
    }
}
