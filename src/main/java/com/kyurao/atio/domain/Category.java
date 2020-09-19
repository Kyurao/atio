package com.kyurao.atio.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Category {

    @Id
    private String name;

    @ManyToOne
    private CategoryType categoryType;
}
