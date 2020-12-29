package com.kyurao.atio.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target(TYPE)
@Retention(RUNTIME)
public @interface PasswordValidation {

    String message() default "Incorrect data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface Password {}

    @Target({ElementType.FIELD})
    @Retention(RetentionPolicy.RUNTIME)
    @interface ConfirmField {

        String message() default "Confirm field doesn't match with field";

        String field();
    }
}