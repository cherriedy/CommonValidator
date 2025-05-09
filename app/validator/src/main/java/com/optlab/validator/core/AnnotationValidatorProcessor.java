package com.optlab.validator.core;

import com.optlab.validator.annotations.ValidationMechanism;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

@SuppressWarnings({"unchecked", "rawtypes"})
public class AnnotationValidatorProcessor {
    public static ValidationResult validate(Object obj) {
        try {
            // Iterate through all fields of the object
            for (Field field : obj.getClass().getDeclaredFields()) {
                field.setAccessible(true); // Make the field accessible
                Object value = field.get(obj); // Get the value of the field

                for (Annotation annotation : field.getAnnotations()) {
                    // Retrieve the ValidationMechanism annotation to get the validator class
                    ValidationMechanism mechanism =
                            annotation.annotationType().getAnnotation(ValidationMechanism.class);

                    if (mechanism != null) {
                        // Create an instance of the validator class using reflection
                        AnnotationValidator validator =
                                mechanism.value().getDeclaredConstructor().newInstance();
                        validator.initialize(annotation); // Initialize the validator

                        ValidationResult result = validator.verify(value);
                        if (!result.isValid()) {
                            return result;
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access field: " + e.getMessage(), e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Failed to invoke constructor: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Illegal argument: " + e.getMessage(), e);
        } catch (InstantiationException e) {
            throw new RuntimeException("Failed to instantiate validator: " + e.getMessage(), e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No such method: " + e.getMessage(), e);
        }
        return ValidationResult.pass();
    }
}
