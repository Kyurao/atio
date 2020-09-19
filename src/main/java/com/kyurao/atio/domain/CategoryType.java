package com.kyurao.atio.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CategoryType {

    @Id
    private String name;
}
