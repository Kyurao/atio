package com.kyurao.atio.domain.user;

import com.kyurao.atio.domain.common.Location;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class ContactInfo {

    private String phoneNumber;

    private Location location;
}
