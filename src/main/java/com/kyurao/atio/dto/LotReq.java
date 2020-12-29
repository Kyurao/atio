package com.kyurao.atio.dto;

import com.kyurao.atio.domain.lot.enums.LotState;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
public class LotReq {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    private String description;

    private BigDecimal startPrice;

    private BigDecimal buyNow;

    private String country;

    private String region;

    private String city;

    private LotState state;

}
