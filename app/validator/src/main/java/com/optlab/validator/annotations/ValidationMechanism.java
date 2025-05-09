package com.optlab.validator.annotations;

import com.optlab.validator.core.AnnotationValidator;
import com.optlab.validator.core.Validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to specify the validation mechanism for a field. This annotation is used to associate
 * a specific validator class with a field, allowing for custom validation logic to be applied.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidationMechanism {
    Class<? extends AnnotationValidator<?>> value();
}
