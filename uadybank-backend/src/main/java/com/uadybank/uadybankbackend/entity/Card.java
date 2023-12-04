package com.uadybank.uadybankbackend.entity;

import com.uadybank.uadybankbackend.enums.CardType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @Size(min = 16, max = 16)
    private String idCard;

    @Column(name = "card_type", nullable = false, columnDefinition = "varchar(255) default 'classic'")
    @Enumerated(EnumType.STRING)
    @NotNull
    private CardType cardType;

    @Column(name = "balance", nullable = false, columnDefinition = "decimal default 0.0")
    @NotNull
    private BigDecimal balance;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private boolean status;

    @Column(name = "vip", nullable = false, columnDefinition = "boolean default false")
    @NotNull
    private boolean vip;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions;

    @Column(name = "creation_date", nullable = false, columnDefinition = "datetime default now()")
    @NotNull
    private LocalDateTime creationDate;

    public boolean getStatus() {
        return status;
    }

    @PrePersist
    private void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

}
