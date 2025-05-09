package com.optlab.validator.annotations;

import com.optlab.validator.validators.MaxLengthValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to indicate the maximum length of a string field. This annotation can be used to
 * validate that a string field does not exceed a specified length.
 */
@ValidationMechanism(MaxLengthValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxLength {
    int value();

    int errorMessageResId() default 0;
}
