package com.optlab.validator.annotations;

import com.optlab.validator.validators.NotEmptyValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Annotation to indicate that a field must not be null and not empty */
@ValidationMechanism(NotEmptyValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmpty {
    int errorMessageResId() default 0;
}
