package com.optlab.validator.validators;

import androidx.annotation.StringRes;

import com.optlab.validator.annotations.MinLength;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.core.Validator;

import java.lang.annotation.Annotation;

public class MinLengthValidator implements Validator<String>, AnnotationValidator<String> {
    private int min;
    private @StringRes int errorMessageResId;

    public MinLengthValidator(int min, @StringRes int errorMessageResId) {
        this.min = min;
        this.errorMessageResId = errorMessageResId;
    }

    @Override
    public void initialize(Annotation annotation) {
        this.min = ((MinLength) annotation).value();
        this.errorMessageResId = ((MinLength) annotation).errorMessageResId();
    }

    @Override
    public ValidationResult verify(String input) {
        return validate(input);
    }

    @Override
    public ValidationResult validate(String input) {
        if (input == null || input.length() < min) {
            return ValidationResult.fail(errorMessageResId);
        }
        return ValidationResult.pass();
    }
}
