package com.optlab.validator.validators;

import androidx.annotation.StringRes;

import com.optlab.validator.annotations.Match;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;
import com.optlab.validator.core.Validator;

import java.lang.annotation.Annotation;
import java.util.Comparator;

public class MatchValidator<T> implements Validator<T> {
    private final T otherValue;
    private final Comparator<T> comparator;
    private final @StringRes int errorMessageResId;

    public MatchValidator(T otherValue) {
        this(otherValue, null, 0);
    }

    public MatchValidator(T otherValue, @StringRes int errorMessageResId) {
        this(otherValue, null, errorMessageResId);
    }

    public MatchValidator(
            T otherValue, Comparator<T> comparator, @StringRes int errorMessageResId) {
        this.otherValue = otherValue;
        this.comparator = comparator;
        this.errorMessageResId = errorMessageResId;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ValidationResult validate(T input) {
        if (input == null || otherValue == null) {
            return ValidationResult.fail(errorMessageResId);
        }

        boolean isValid;
        if (comparator != null) {
            isValid = comparator.compare(input, otherValue) == 0;
        } else if (input instanceof Comparable) {
            isValid = ((Comparable<T>) input).compareTo(otherValue) == 0;
        } else {
            throw new IllegalArgumentException(
                    "Input type does not implement Comparable and no comparator provided");
        }
        return isValid ? ValidationResult.pass() : ValidationResult.fail(errorMessageResId);
    }
}
