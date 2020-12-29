package com.kyurao.atio.dto;

import com.kyurao.atio.validation.EmailExist;
import com.kyurao.atio.validation.PasswordValidation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@PasswordValidation
public class RegistrationReq {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email is incorrect")
    @EmailExist(message = "User with such email already exist")
    private String email;

    @PasswordValidation.Password
    private String firstPassword;

    @PasswordValidation.ConfirmField(
            field = "firstPassword",
            message = "Confirm password doesn't match with password")
    private String secondPassword;
}
