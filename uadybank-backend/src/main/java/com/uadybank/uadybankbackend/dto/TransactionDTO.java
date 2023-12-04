package com.uadybank.uadybankbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private Long idTransaction;
    private BigDecimal amount;
    private String description;
    private String transactionType;
    private LocalDateTime transactionDate;
    private String destinationCard;

}
