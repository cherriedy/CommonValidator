package com.optlab.validator.annotations;

import androidx.annotation.StringRes;

import com.optlab.validator.validators.LowercaseValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValidationMechanism(LowercaseValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Lowercase {
    @StringRes
    int errorMessageResId();
}
