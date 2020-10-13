package com.kyurao.atio.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "USERS")
public class User {

    @Id
    private Long id;

    @Column
    private String name;

    private Account account;

    private ContactInfo contactInfo;
}
