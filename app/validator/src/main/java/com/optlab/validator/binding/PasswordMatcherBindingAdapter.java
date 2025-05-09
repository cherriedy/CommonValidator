package com.optlab.validator.binding;

import android.text.TextUtils;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.databinding.BindingAdapter;

import com.google.android.material.textfield.TextInputLayout;
import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.validators.MatchValidator;

public class PasswordMatcherBindingAdapter {
    @BindingAdapter(value = {"password", "confirmPassword", "errorMessageResId"})
    public static void validatePasswordMatch(
            @NonNull TextInputLayout textInputLayout,
            @NonNull String password,
            @NonNull String confirmPassword,
            @StringRes int errorMessageResId) {
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            textInputLayout.setError(null);
            return;
        }

        MatchValidator<String> validator = new MatchValidator<>(password);
        ValidationResult result = validator.validate(confirmPassword);

        if (!result.isValid()) {
            textInputLayout.setError(textInputLayout.getContext().getText(errorMessageResId));
        } else {
            textInputLayout.setError(null);
        }
    }

    @BindingAdapter(value = {"password", "confirmPassword", "errorMessageResId"})
    public static void validatePassword(
            @NonNull EditText editText,
            @NonNull String password,
            @NonNull String confirmPassword,
            @StringRes int errorMessageResId) {
        if (TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)) {
            editText.setError(null);
            return;
        }

        MatchValidator<String> validator = new MatchValidator<>(password);
        ValidationResult result = validator.validate(confirmPassword);
        if (!result.isValid()) {
            editText.setError(editText.getContext().getText(errorMessageResId));
        } else {
            editText.setError(null);
        }
    }
}
