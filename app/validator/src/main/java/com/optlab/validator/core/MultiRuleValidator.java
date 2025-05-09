package com.optlab.validator.core;

import java.util.List;

public class MultiRuleValidator<T> implements Validator<T> {
    private final List<Validator<T>> validators;

    public MultiRuleValidator(List<Validator<T>> validators) {
        this.validators = validators;
    }

    @Override
    public ValidationResult validate(T input) {
        for (Validator<T> validator : validators) {
            ValidationResult result = validator.validate(input);
            if (!result.isValid()) {
                return result;
            }
        }
        return ValidationResult.pass();
    }
}
