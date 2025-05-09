package com.optlab.validator.validators;

import com.optlab.validator.annotations.Lowercase;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;

import java.lang.annotation.Annotation;

/** Validator that checks if the input contains at least one uppercase letter. */
public class LowercaseValidator extends PatternValidator implements AnnotationValidator<String> {
    /** Pattern to check for at least one lowercase letter. */
    private static final String PATTERN = ".*[a-z].*";

    public LowercaseValidator(int errorMessageResId) {
        super(PATTERN, errorMessageResId);
    }

    @Override
    public void initialize(Annotation annotation) {
        super.errorMessageResId = ((Lowercase) annotation).errorMessageResId();
    }

    @Override
    public ValidationResult verify(String input) {
        return validate(input);
    }
}
