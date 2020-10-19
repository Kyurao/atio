package com.kyurao.atio.domain.common;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@EqualsAndHashCode
@Embeddable
public class Location {

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;
}
