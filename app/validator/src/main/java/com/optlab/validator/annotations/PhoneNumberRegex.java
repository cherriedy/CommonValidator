package com.optlab.validator.annotations;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.optlab.validator.validators.RegexPhoneNumberValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValidationMechanism(RegexPhoneNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumberRegex {
    @NonNull
    String pattern();

    @StringRes
    int errorMessageResId();
}
