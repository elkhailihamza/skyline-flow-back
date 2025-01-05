package org.project.skyflow.util.annotation.implementation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.project.skyflow.dto.AuthDTO;
import org.project.skyflow.util.annotation.PasswordMatch;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value instanceof AuthDTO dto) {
            return dto.getPassword() != null && dto.getPassword().equals(dto.getConfirmPassword());
        }
        return false;
    }
}
