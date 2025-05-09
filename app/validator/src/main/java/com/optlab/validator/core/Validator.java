package com.optlab.validator.core;

public interface Validator<T> {
    ValidationResult validate(T input);
}
