package com.kyurao.atio.domain.lot;

import com.kyurao.atio.domain.common.IdHolder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(
        name = "category",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "type"}))
public class Category extends IdHolder {

    @Column(nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "type")
    private CategoryType type;
}
