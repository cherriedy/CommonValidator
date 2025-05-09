package com.optlab.validator.core;

import androidx.annotation.StringRes;

/**
 * Represents the result of a validation operation.
 *
 * <p>This class encapsulates the result of a validation check, indicating whether the validation
 * passed or failed, and providing an optional error message resource ID in case of failure.
 */
public class ValidationResult {
    private final boolean valid;
    private final @StringRes int errorMessageResId;

    public ValidationResult(boolean valid) {
        this(valid, 0);
    }

    public ValidationResult(boolean valid, @StringRes int errorMessageResId) {
        this.valid = valid;
        this.errorMessageResId = errorMessageResId;
    }

    /** Returns a ValidationResult that indicates the validation passed. */
    public static ValidationResult pass() {
        return new ValidationResult(true, 0);
    }

    /** Returns a ValidationResult that indicates the validation failed. */
    public static ValidationResult fail(@StringRes int errorMessageResId) {
        return new ValidationResult(false, errorMessageResId);
    }

    public boolean isValid() {
        return valid;
    }

    public int getErrorMessageResId() {
        return errorMessageResId;
    }
}
