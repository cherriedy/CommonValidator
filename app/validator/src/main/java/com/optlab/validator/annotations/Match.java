package com.optlab.validator.annotations;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;

import com.optlab.validator.validators.MatchValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Comparator;

/**
 * Annotation to indicate that a field must match another field. This annotation can be used in
 * conjunction with a validation framework to enforce matching field validation.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Match {
    @NonNull
    String otherFieldName();

    Class<? extends Comparator> comparator() default Comparator.class;

    @StringRes
    int errorMessageResId();
}
