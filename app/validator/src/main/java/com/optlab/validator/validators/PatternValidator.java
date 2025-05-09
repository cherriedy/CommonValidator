package com.optlab.validator.validators;

import androidx.annotation.StringRes;

import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.core.Validator;

/** A validator that checks if a given string matches a specified pattern. */
public class PatternValidator implements Validator<String> {
    protected String pattern;
    protected @StringRes int errorMessageResId;

    /**
     * Constructs a PatternValidator with the specified pattern and error message resource ID.
     *
     * @param pattern The regex pattern to match against.
     * @param errorMessageResId The resource ID of the error message to display if validation fails.
     */
    public PatternValidator(String pattern, int errorMessageResId) {
        this.pattern = pattern;
        this.errorMessageResId = errorMessageResId;
    }

    /**
     * Validates the input string against the specified pattern.
     *
     * @param input The input string to validate.
     * @return A ValidationResult indicating whether the validation passed or failed.
     */
    @Override
    public ValidationResult validate(String input) {
        if (input.matches(pattern)) {
            return ValidationResult.pass();
        } else {
            return ValidationResult.fail(errorMessageResId);
        }
    }
}
