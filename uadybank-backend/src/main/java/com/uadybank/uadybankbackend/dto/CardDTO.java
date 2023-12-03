package com.uadybank.uadybankbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CardDTO {

    private Long idCard;
    private String cardType;
    private BigDecimal balance;
    private boolean vip;

}
