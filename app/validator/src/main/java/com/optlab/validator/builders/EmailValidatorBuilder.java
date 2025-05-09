package com.optlab.validator.builders;

import androidx.annotation.StringRes;

import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.core.Validator;
import com.optlab.validator.validators.EmailValidator;
import com.optlab.validator.validators.NotEmptyValidator;

import java.util.ArrayList;
import java.util.List;

public class EmailValidatorBuilder {
    private final List<Validator<String>> validators = new ArrayList<>();

    /** Adds a validator to check if the email is not null. */
    public EmailValidatorBuilder requireNonNull(@StringRes int errorMessageResId) {
        validators.add(new NotEmptyValidator(errorMessageResId));
        return this;
    }

    public EmailValidatorBuilder requireValidEmail(@StringRes int errorMessageResId) {
        validators.add(new EmailValidator(errorMessageResId));
        return this;
    }

    public Validator<String> build() {
        return input -> {
            for (Validator<String> validator : validators) {
                ValidationResult result = validator.validate(input);
                if (!result.isValid()) {
                    return result;
                }
            }
            return ValidationResult.pass();
        };
    }
}
