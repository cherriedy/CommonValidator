package com.optlab.validator.validators;

import androidx.annotation.StringRes;

import com.optlab.validator.annotations.MaxLength;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.core.Validator;

import java.lang.annotation.Annotation;

/** Validator that checks if the input string exceeds a maximum length. */
public class MaxLengthValidator implements Validator<String>, AnnotationValidator<String> {
    private int max;
    private @StringRes int errorMessageResId;

    /**
     * Constructor for MaxLengthValidator.
     *
     * @param max the maximum length allowed
     * @param errorMessageResId the resource ID of the error message to be shown if validation fails
     */
    public MaxLengthValidator(int max, int errorMessageResId) {
        this.max = max;
        this.errorMessageResId = errorMessageResId;
    }

    @Override
    public void initialize(Annotation annotation) {
        this.max = ((MaxLength) annotation).value();
        this.errorMessageResId = ((MaxLength) annotation).errorMessageResId();
    }

    @Override
    public ValidationResult verify(String input) {
        return validate(input);
    }

    /**
     * Validates the input string to check if it exceeds the maximum length.
     *
     * @param input the input string to validate
     * @return ValidationResult indicating whether the input is valid or not
     */
    @Override
    public ValidationResult validate(String input) {
        if (input == null || input.length() > max) {
            return ValidationResult.fail(errorMessageResId);
        }
        return ValidationResult.pass();
    }
}
