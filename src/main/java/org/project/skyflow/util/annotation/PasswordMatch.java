package org.project.skyflow.util.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.project.skyflow.util.annotation.implementation.PasswordMatchValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchValidator.class)
public @interface PasswordMatch {
    String message() default "Password and confirmation do not match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
