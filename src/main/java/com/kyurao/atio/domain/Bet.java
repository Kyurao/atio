package com.kyurao.atio.domain;

import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.time.Instant;

@Embeddable
public class Bet {

    private BigDecimal value;

    private Instant time;
}
