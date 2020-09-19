package com.kyurao.atio.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ContactInfo {

    @Column(nullable = false)
    private String email;

    private String phoneNumber;

    private Address address;
}
