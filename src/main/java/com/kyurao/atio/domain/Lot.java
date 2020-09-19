package com.kyurao.atio.domain;

import com.kyurao.atio.domain.enums.LotState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "LOT")
public class Lot {

    @Id
    private Long id;

    private String name;

    private String description;

    private Integer amount;

    private Instant startOfSale;

    private Instant endOfSale;

    private BigDecimal startPrice = new BigDecimal(0);

    private BigDecimal price = new BigDecimal(0);

    private BigDecimal buyNow;

    private Location location;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private LotState state = LotState.NEW;

    @ElementCollection
    @CollectionTable
    private List<Image> images = new ArrayList<>();

    @OneToMany
    private Set<Category> categories = new HashSet<>();
}
