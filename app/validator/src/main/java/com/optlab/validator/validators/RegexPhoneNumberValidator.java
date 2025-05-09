package com.optlab.validator.validators;

import com.optlab.validator.annotations.PhoneNumberRegex;
import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.ValidationResult;

import java.lang.annotation.Annotation;

public class RegexPhoneNumberValidator extends PatternValidator
        implements AnnotationValidator<String> {
    /**
     * Validates a phone number using a regex pattern.
     *
     * @param pattern The regex pattern to validate the phone number.
     * @param errorMessageResId The resource ID of the error message to be shown if validation
     *     fails.
     */
    public RegexPhoneNumberValidator(String pattern, int errorMessageResId) {
        super(pattern, errorMessageResId);
    }

    @Override
    public void initialize(Annotation annotation) {
        super.pattern = ((PhoneNumberRegex) annotation).pattern();
        super.errorMessageResId = ((PhoneNumberRegex) annotation).errorMessageResId();
    }

    @Override
    public ValidationResult verify(String input) {
        return validate(input);
    }
}
