package com.kyurao.atio.domain.user;

import com.kyurao.atio.domain.user.Address;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Embeddable
public class ContactInfo {

    @EqualsAndHashCode.Include
    @Column(nullable = false, unique = true)
    private String email;

    private String phoneNumber;

    private Address address;
}
