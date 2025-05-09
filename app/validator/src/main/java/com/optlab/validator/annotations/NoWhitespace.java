package com.optlab.validator.annotations;

import androidx.annotation.StringRes;

import com.optlab.validator.validators.NoWhitespaceValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValidationMechanism(NoWhitespaceValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NoWhitespace {
    @StringRes
    int errorMessageResId();
}
