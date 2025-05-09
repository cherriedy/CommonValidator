package com.optlab.validator.builders;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.core.Validator;
import com.optlab.validator.validators.LibPhoneNumberValidator;
import com.optlab.validator.validators.NotEmptyValidator;
import com.optlab.validator.validators.RegexPhoneNumberValidator;

import java.util.ArrayList;
import java.util.List;

public class PhoneNumberValidatorBuilder {
    private final List<Validator<String>> validators = new ArrayList<>();

    /** Adds a validator to check if the phone number is not null. */
    public PhoneNumberValidatorBuilder requireNotNull(@StringRes int errorMessageResId) {
        validators.add(new NotEmptyValidator(errorMessageResId));
        return this;
    }

    public PhoneNumberValidatorBuilder requireValidPhoneNumber(
            @NonNull String countryCode,
            @StringRes int phoneNumberErrorMessageResId,
            @StringRes int phoneNumberFormatErrorMessageResId) {
        // Using Google LibPhoneNumber for phone number validation
        LibPhoneNumberValidator libPhoneNumberValidator =
                new LibPhoneNumberValidator.Builder()
                        .setCountryCode(countryCode) // e.g., "US" for United States
                        .setPhoneNumberErrorMessage(phoneNumberErrorMessageResId)
                        .setPhoneNumberFormatErrorMessage(phoneNumberFormatErrorMessageResId)
                        .build();
        validators.add(libPhoneNumberValidator);
        return this;
    }

    public PhoneNumberValidatorBuilder requireValidPhoneNumberRegex(
            @NonNull String pattern, @StringRes int errorMessageResId) {
        validators.add(new RegexPhoneNumberValidator(pattern, errorMessageResId));
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
