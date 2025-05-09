package com.optlab.validator.validators;

import androidx.annotation.StringRes;

import com.optlab.validator.annotations.NotEmpty;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.core.Validator;

import java.lang.annotation.Annotation;

public class NotEmptyValidator implements Validator<String>, AnnotationValidator<String> {
    private @StringRes int errorMessageResId;

    public NotEmptyValidator() {}

    public NotEmptyValidator(int errorMessageResId) {
        this.errorMessageResId = errorMessageResId;
    }

    @Override
    public void initialize(Annotation annotation) {
        if (annotation instanceof NotEmpty) {
            this.errorMessageResId = ((NotEmpty) annotation).errorMessageResId();
        } else {
            throw new IllegalArgumentException("Invalid annotation type");
        }
    }

    @Override
    public ValidationResult verify(String input) {
        if (input == null) {
            return ValidationResult.fail(errorMessageResId);
        }
        return validate(input);
    }

    @Override
    public ValidationResult validate(String input) {
        if (input == null || input.isEmpty()) {
            return ValidationResult.fail(errorMessageResId);
        } else {
            return ValidationResult.pass();
        }
    }
}
