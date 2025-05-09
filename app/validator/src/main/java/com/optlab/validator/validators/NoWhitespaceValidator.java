package com.optlab.validator.validators;

import com.optlab.validator.annotations.NoWhitespace;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;

import java.lang.annotation.Annotation;

public class NoWhitespaceValidator extends PatternValidator implements AnnotationValidator<String> {
    private static final String PATTERN = ".*\\s.*";

    public NoWhitespaceValidator(int errorMessageResId) {
        super(PATTERN, errorMessageResId);
    }

    @Override
    public void initialize(Annotation annotation) {
        super.errorMessageResId = ((NoWhitespace) annotation).errorMessageResId();
    }

    @Override
    public ValidationResult verify(String input) {
        return validate(input);
    }
}
