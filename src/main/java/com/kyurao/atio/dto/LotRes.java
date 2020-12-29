package com.kyurao.atio.dto;

import com.kyurao.atio.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Getter
@Setter
public class LotRes {

    @NotBlank(message = "Title cannot be empty")
    private String title;

    private BigDecimal startPrice;

    private BigDecimal price;

    private Integer betCount;

    private User seller;


}
