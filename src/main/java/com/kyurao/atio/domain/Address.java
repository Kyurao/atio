package com.kyurao.atio.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private Location location;

    private String street;

    private String number;

    private String apartment;

    private String zipCode;
}
