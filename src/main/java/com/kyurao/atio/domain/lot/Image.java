package com.kyurao.atio.domain.lot;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class Image {

    @Column(nullable = false)
    private String originalName;

    @Column(nullable = false)
    private String path;

}
