package com.kyurao.atio.domain.lot;

import com.kyurao.atio.domain.user.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class Bet {

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private Instant time = Instant.now();

    @ManyToOne(optional = false)
    @JoinColumn
    private User buyer;
}
