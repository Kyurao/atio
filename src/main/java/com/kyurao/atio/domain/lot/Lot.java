package com.kyurao.atio.domain.lot;

import com.kyurao.atio.domain.common.IdHolder;
import com.kyurao.atio.domain.common.Location;
import com.kyurao.atio.domain.lot.enums.LotState;
import com.kyurao.atio.domain.lot.enums.LotStatus;
import com.kyurao.atio.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "lot")
public class Lot extends IdHolder {

    private String tittle;

    private String description;

    private Integer amount;

    private BigDecimal startPrice = new BigDecimal(0);

    private BigDecimal price = new BigDecimal(0);

    private BigDecimal buyNow;

    @AttributeOverrides({
            @AttributeOverride(name = "country", column = @Column(name = "COUNTRY", nullable = false)),
            @AttributeOverride(name = "region", column = @Column(name = "REGION", nullable = false)),
            @AttributeOverride(name = "city", column = @Column(name = "CITY", nullable = false))
    } )
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn
    private User seller;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private LotState state = LotState.NEW;

    @Enumerated(value = EnumType.STRING)
    @Column(length = 20, nullable = false)
    private LotStatus status = LotStatus.ACTIVE;

    @ElementCollection
    @CollectionTable
    private Set<Image> images = new HashSet<>();

    @ElementCollection
    @CollectionTable
    private Set<Bet> bets = new HashSet<>();

    @OneToMany
    @JoinTable(name = "lot_category",
            joinColumns = @JoinColumn(name = "lot_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();
}
