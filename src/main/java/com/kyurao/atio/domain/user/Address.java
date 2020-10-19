package com.kyurao.atio.domain.user;

import com.kyurao.atio.domain.common.Location;
import lombok.EqualsAndHashCode;

import javax.persistence.Embeddable;

@EqualsAndHashCode
@Embeddable
public class Address {

    private Location location;

    private String street;

    private String number;

    private String apartment;

    private String zipCode;
}
