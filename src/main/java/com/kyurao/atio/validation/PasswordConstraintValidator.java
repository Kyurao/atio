package com.kyurao.atio.validation;

import org.passay.*;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class PasswordConstraintValidator implements ConstraintValidator<PasswordValidation, Object> {

    private static final String NOT_STRING = "It is not a string";
    private String defaultMessage;
    private ThreadLocal<Boolean> valid = new ThreadLocal<>();

    @Override
    public void initialize(PasswordValidation passwordValidation) {
        this.defaultMessage = passwordValidation.message();
    }

    @Override
    public boolean isValid(Object candidate, ConstraintValidatorContext context) {
            this.valid.set(true);
            var fields = candidate.getClass().getDeclaredFields();
            Arrays.stream(fields)
                    .filter(this::isPasswordField)
                    .forEach(field -> validatePasswordField(field, candidate, context));
            Arrays.stream(fields)
                    .filter(this::isConfirmField)
                    .forEach(field -> validateConfirmField(field, candidate, context));

            if (!this.valid.get()) {
                context.buildConstraintViolationWithTemplate(this.defaultMessage)
                        .addConstraintViolation()
                        .disableDefaultConstraintViolation();
            }

        return this.valid.get();
    }

    private boolean isConfirmField(Field field) {
        return field.isAnnotationPresent(PasswordValidation.ConfirmField.class);
    }

    private boolean isPasswordField(Field field) {
        return field.isAnnotationPresent(PasswordValidation.Password.class);
    }

    private void validatePasswordField(Field field,
                                       Object candidate,
                                       ConstraintValidatorContext context) {
        var passwordObject = getValue(field, candidate);
        var fieldName = field.getName();
        if (passwordObject.getClass() == String.class) {
            var password = (String) passwordObject;
            List<Rule> rules = List.of(
                    // no whitespace
                    new WhitespaceRule()
                    // at least 8 characters
//                    new LengthRule(8, 30),
                    // at least one upper-case character
//                    new CharacterRule(EnglishCharacterData.UpperCase, 1),
                    // at least one lower-case character
//                    new CharacterRule(EnglishCharacterData.LowerCase, 1));
                    // at least one digit character
//                    new CharacterRule(EnglishCharacterData.Digit, 1),
                    // at least one symbol (special character)
//                    new CharacterRule(EnglishCharacterData.Special, 1)
                    );

            var validator = new PasswordValidator(rules);
            var result = validator.validate(new PasswordData(password));
            if (!result.isValid()) {
                this.valid.set(false);
                var messageTemplate = String.join(",\n", validator.getMessages(result));
                addErrorMessageForField(fieldName, context, messageTemplate);
            }
        } else {
            this.valid.set(false);
            addErrorMessageForField(fieldName, context, NOT_STRING);
        }
    }

    private Object getValue(Field field, Object o) {
        try {
            field.setAccessible(true);
            return field.get(o);
        } catch (IllegalAccessException e) {
            return new Object();
        }
    }

    private void addErrorMessageForField(String fieldName,
                                         ConstraintValidatorContext context,
                                         String messageTemplate) {
        context.buildConstraintViolationWithTemplate(messageTemplate)
                .addPropertyNode(fieldName)
                .addConstraintViolation()
                .disableDefaultConstraintViolation();
    }

    private void validateConfirmField(Field field,
                                      Object candidate,
                                      ConstraintValidatorContext context) {
        var confirmValue = getValue(field, candidate);
        var confirmFieldName = field.getName();
        if (confirmValue.getClass() == String.class) {
            var message = field.getAnnotation(PasswordValidation.ConfirmField.class).message();
            var fieldName = field.getAnnotation(PasswordValidation.ConfirmField.class).field();
            var value = getFieldValueByNameFrom(fieldName, candidate);
            if(!confirmValue.equals(value)) {
                addErrorMessageForField(confirmFieldName, context, message);
                this.valid.set(false);
            }
        } else {
            this.valid.set(false);
            addErrorMessageForField(confirmFieldName, context, NOT_STRING);
        }
    }

    private Object getFieldValueByNameFrom(String fieldName, Object o) {
        try {
            var clazz = o.getClass();
            var field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(o);
        } catch (Exception e) {
            return new Object();
        }
    }
}