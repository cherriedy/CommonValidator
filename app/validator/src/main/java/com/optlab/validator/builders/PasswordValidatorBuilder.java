package com.optlab.validator.builders;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.optlab.validator.core.MultiRuleValidator;
import com.optlab.validator.core.Validator;
import com.optlab.validator.validators.DigitValidator;
import com.optlab.validator.validators.LowercaseValidator;
import com.optlab.validator.validators.MaxLengthValidator;
import com.optlab.validator.validators.MinLengthValidator;
import com.optlab.validator.validators.NoWhitespaceValidator;
import com.optlab.validator.validators.NotEmptyValidator;
import com.optlab.validator.validators.PatternValidator;
import com.optlab.validator.validators.SpecialCharValidator;
import com.optlab.validator.validators.UppercaseValidator;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidatorBuilder {
    private final List<Validator<String>> validators = new ArrayList<>();

    /** Require the password to be not null. */
    public PasswordValidatorBuilder requireNotNull(@StringRes int errorMessageResId) {
        validators.add(new NotEmptyValidator(errorMessageResId));
        return this;
    }

    /** Sets the minimum length of the password. */
    public PasswordValidatorBuilder requireMinLength(int min, @StringRes int errorMessageResId) {
        validators.add(new MinLengthValidator(min, errorMessageResId));
        return this;
    }

    /** Sets the maximum length of the password. */
    public PasswordValidatorBuilder requireMaxLength(int max, @StringRes int errorMessageResId) {
        validators.add(new MaxLengthValidator(max, errorMessageResId));
        return this;
    }

    /** Require at least one uppercase letter in the password. */
    public PasswordValidatorBuilder requireUppercase(@StringRes int errorMessageResId) {
        validators.add(new UppercaseValidator(errorMessageResId));
        return this;
    }

    /** Require at least one lowercase letter in the password. */
    public PasswordValidatorBuilder requireLowercase(@StringRes int errorMessageResId) {
        validators.add(new LowercaseValidator(errorMessageResId));
        return this;
    }

    /** Require at least one digit in the password. */
    public PasswordValidatorBuilder requireDigit(@StringRes int errorMessageResId) {
        validators.add(new DigitValidator(errorMessageResId));
        return this;
    }

    /** Require at least one special character in the password. */
    public PasswordValidatorBuilder requireSpecialChar(@StringRes int errorMessageResId) {
        validators.add(new SpecialCharValidator(errorMessageResId));
        return this;
    }

    /** Require not to contain whitespace characters in the password. */
    public PasswordValidatorBuilder disallowWhitespace(@StringRes int errorMessageResId) {
        validators.add(new NoWhitespaceValidator(errorMessageResId));
        return this;
    }

    /** Require the password to match a specific pattern. */
    public PasswordValidatorBuilder requirePattern(
            @NonNull String pattern, @StringRes int errorMessageResId) {
        validators.add(new PatternValidator(pattern, errorMessageResId));
        return this;
    }

    public Validator<String> build() {
        return input -> new MultiRuleValidator<>(validators).validate(input);
    }
}
