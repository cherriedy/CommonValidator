package com.optlab.validator.annotations;

import com.optlab.validator.validators.DigitValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValidationMechanism(DigitValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Digit {
    int errorMessageResId() default 0;
}
