package com.optlab.validator.annotations;

import androidx.annotation.StringRes;

import com.optlab.validator.validators.EmailValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate that a field must be a valid email address. This annotation can be used in
 * conjunction with a validation framework to enforce email format validation.
 */
@ValidationMechanism(EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {
    @StringRes
    int errorMessageResId();
}
