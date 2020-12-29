package com.kyurao.atio.validation;

import com.kyurao.atio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
@Component
public class EmailExistConstraintValidator implements ConstraintValidator<EmailExist, String> {

    private final UserRepository userRepository;

    @Override
    public void initialize(EmailExist emailExist) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
            return !userRepository.existsByAccountEmailIgnoreCase(email);
    }

}