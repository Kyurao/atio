package com.kyurao.atio.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Image {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String path;
}
