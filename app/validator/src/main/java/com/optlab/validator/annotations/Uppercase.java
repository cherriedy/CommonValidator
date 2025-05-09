package com.optlab.validator.annotations;

import androidx.annotation.StringRes;

import com.optlab.validator.validators.UppercaseValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValidationMechanism(UppercaseValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Uppercase {
    @StringRes
    int errorMessageResId();
}
