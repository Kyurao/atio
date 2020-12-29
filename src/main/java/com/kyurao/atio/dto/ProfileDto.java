package com.kyurao.atio.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class ProfileDto {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Pattern(regexp = "^(?:\\+38)?(?:\\([0-9]{3}\\)[ .-]?[0-9]{3}[ .-]?[0-9]{2}[ .-]?[0-9]{2}|[0-9]{3}[ .-]?[0-9]{3}[ .-]?[0-9]{2}[ .-]?[0-9]{2}|[0-9]{3}[0-9]{7})$",
            message = "Wrong format for phone number. Use +38(000)000-00-00")
    private String phoneNumber;

    private String country;

    private String region;

    private String city;
}
