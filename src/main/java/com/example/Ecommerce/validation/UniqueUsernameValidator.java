package com.example.Ecommerce.validation;

import com.example.Ecommerce.annotation.UniqueUsername;
import com.example.Ecommerce.repository.UsuarioRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (usuarioRepository.findByUsername(s) == null) {
            return true;
        }
        return false;
    }
}