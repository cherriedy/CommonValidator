package com.optlab.validator.annotations;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.optlab.validator.validators.LibPhoneNumberValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@ValidationMechanism(LibPhoneNumberValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    @StringRes
    int errorMessageResId();

    @StringRes
    int phoneNumberFormatErrorMessageResId();

    @NonNull
    String countryCode();
}
