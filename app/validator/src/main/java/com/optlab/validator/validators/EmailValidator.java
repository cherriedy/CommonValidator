package com.optlab.validator.validators;

import com.optlab.validator.annotations.Email;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.core.Validator;

import java.lang.annotation.Annotation;
import java.util.regex.Pattern;

/** Validator that checks if a given string is a valid email address. */
public class EmailValidator implements Validator<String>, AnnotationValidator<String> {
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private int errorMessageResId;

    public EmailValidator() {}

    public EmailValidator(int errorMessageResId) {
        this.errorMessageResId = errorMessageResId;
    }

    @Override
    public void initialize(Annotation annotation) {
        if (annotation instanceof Email) {
            this.errorMessageResId = ((Email) annotation).errorMessageResId();
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
        if (!EMAIL_PATTERN.matcher(input).matches()) {
            return ValidationResult.fail(errorMessageResId);
        }
        return ValidationResult.pass();
    }
}
