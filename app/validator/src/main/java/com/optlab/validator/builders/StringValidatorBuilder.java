package com.optlab.validator.builders;

import androidx.annotation.StringRes;

import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.core.Validator;
import com.optlab.validator.validators.MaxLengthValidator;
import com.optlab.validator.validators.MinLengthValidator;
import com.optlab.validator.validators.NotEmptyValidator;

import java.util.ArrayList;
import java.util.List;

public class StringValidatorBuilder {
    private final List<Validator<String>> validators = new ArrayList<>();

    /** Adds a validator to check if the string is not null. */
    public StringValidatorBuilder requireNotNull(@StringRes int errorMessageResId) {
        validators.add(new NotEmptyValidator(errorMessageResId));
        return this;
    }

    /** Adds a validator to check if the string has a minimum length. */
    public StringValidatorBuilder requireMinLength(int min, @StringRes int errorMessageResId) {
        validators.add(new MinLengthValidator(min, errorMessageResId));
        return this;
    }

    /** Adds a validator to check if the string has a maximum length. */
    public StringValidatorBuilder requireMaxLength(int max, @StringRes int errorMessageResId) {
        validators.add(new MaxLengthValidator(max, errorMessageResId));
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
