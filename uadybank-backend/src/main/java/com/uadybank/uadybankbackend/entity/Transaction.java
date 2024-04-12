package com.uadybank.uadybankbackend.entity;

import com.uadybank.uadybankbackend.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaction;

    @Column(name = "amount", nullable = false, columnDefinition = "decimal default 0.0")
    @NotNull
    private BigDecimal amount;

    @Column(name = "description", columnDefinition = "VARCHAR(255) default ''")
    private String description;

    @Column(name = "transaction_type", nullable = false, columnDefinition = "VARCHAR(255) default ''")
    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType transactionType;

    @Column(name = "transaction_date", nullable = false, columnDefinition = "datetime default now()")
    private LocalDateTime transactionDate;

    @Column(name = "destination", columnDefinition = "bigint default 0")
    private String destination;

    @PrePersist
    private void setTransactionDate() {
        this.transactionDate = LocalDateTime.now();
    }

}
