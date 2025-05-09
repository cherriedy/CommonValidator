package com.optlab.validator.annotations;

import androidx.annotation.StringRes;

import com.optlab.validator.validators.SpecialCharValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValidationMechanism(SpecialCharValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SpecialChar {
    @StringRes
    int errorMessageResId();
}
