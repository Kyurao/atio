package com.kyurao.atio.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Account {

    @Column(unique = true, nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;
}
