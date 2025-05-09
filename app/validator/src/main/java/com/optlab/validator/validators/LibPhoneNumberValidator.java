package com.optlab.validator.validators;

import android.text.TextUtils;

import androidx.annotation.StringRes;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.optlab.validator.annotations.PhoneNumber;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.core.Validator;

import java.lang.annotation.Annotation;

public class LibPhoneNumberValidator implements Validator<String>, AnnotationValidator<String> {
    /** An instance of PhoneNumberUtil used for phone number validation */
    private static final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    /** Error message resource ID for invalid phone number format. */
    private @StringRes int phoneNumberErrorMessageResId;

    /** Error message resource ID for invalid phone number in specific country. */
    private @StringRes int phoneNumberFormatErrorMessageResId;

    /**
     * The country code for the phone number. This should be a valid ISO 3166-1 alpha-2 country
     * code. For example, "US" for United States, "IN" for India, etc.
     */
    private String countryCode;

    private LibPhoneNumberValidator(Builder builder) {
        this.phoneNumberErrorMessageResId = builder.phoneNumberErrorMessageResId;
        this.phoneNumberFormatErrorMessageResId = builder.phoneNumberFormatErrorMessageResId;
        this.countryCode = builder.countryCode;
    }

    @Override
    public void initialize(Annotation annotation) {
        this.phoneNumberErrorMessageResId = ((PhoneNumber) annotation).errorMessageResId();
        this.phoneNumberFormatErrorMessageResId =
                ((PhoneNumber) annotation).phoneNumberFormatErrorMessageResId();
        this.countryCode = ((PhoneNumber) annotation).countryCode();
    }

    @Override
    public ValidationResult verify(String input) {
        return validate(input);
    }

    public static class Builder {
        private @StringRes int phoneNumberErrorMessageResId;
        private @StringRes int phoneNumberFormatErrorMessageResId;
        private String countryCode;

        /** Sets the error message resource ID for invalid phone number in general. */
        public Builder setPhoneNumberErrorMessage(@StringRes int errorMessageResId) {
            this.phoneNumberErrorMessageResId = errorMessageResId;
            return this;
        }

        /**
         * Sets the error message resource ID for invalid phone number format in specific country.
         */
        public Builder setPhoneNumberFormatErrorMessage(@StringRes int errorMessageResId) {
            this.phoneNumberFormatErrorMessageResId = errorMessageResId;
            return this;
        }

        /** Sets the country code for the phone number. */
        public Builder setCountryCode(String countryCode) {
            this.countryCode = countryCode.toUpperCase();
            return this;
        }

        public LibPhoneNumberValidator build() {
            return new LibPhoneNumberValidator(this);
        }
    }

    @Override
    public ValidationResult validate(String input) {
        try {
            // Check if the LibPhoneNumber library is available.
            Class.forName("com.google.i18n.phonenumbers.PhoneNumberUtil");

            // Check if the country code is valid.
            if (isValidCountryCode()) {
                // Parse the phone number using the provided country code
                Phonenumber.PhoneNumber number = phoneNumberUtil.parse(input, countryCode);
                if (phoneNumberUtil.isValidNumber(number)) {
                    if (phoneNumberUtil.isValidNumberForRegion(number, countryCode)) {
                        // Check if the phone number is valid for the specified region.
                        return ValidationResult.pass();
                    } else {
                        // Phone number is valid but not for the specified region.
                        return ValidationResult.fail(phoneNumberFormatErrorMessageResId);
                    }
                } else {
                    // Phone number is invalid in general.
                    return ValidationResult.fail(phoneNumberErrorMessageResId);
                }
            } else {
                throw new RuntimeException("Invalid country code: " + countryCode);
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(
                    "LibPhoneNumber library is not available: " + e.getMessage());
        } catch (NumberParseException e) {
            throw new RuntimeException("Error parsing phone number: " + e.getMessage());
        }
    }

    /**
     * Checks if the provided country code is valid.
     *
     * <p>This method checks if the country code is not empty and if it is supported by the {@link
     * PhoneNumberUtil} instance. It returns true if the country code is valid, false otherwise.
     */
    private boolean isValidCountryCode() {
        return !TextUtils.isEmpty(countryCode)
                && phoneNumberUtil.getSupportedRegions().contains(countryCode);
    }
}
