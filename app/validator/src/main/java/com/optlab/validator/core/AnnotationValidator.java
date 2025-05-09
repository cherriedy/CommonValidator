package com.optlab.validator.core;

import java.lang.annotation.Annotation;

public interface AnnotationValidator<T> {
    void initialize(Annotation annotation);

    ValidationResult verify(T input);
}
