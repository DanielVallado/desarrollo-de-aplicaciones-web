package com.uadybank.uadybankbackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCard;

    @Column(name = "typeCard", nullable = false, columnDefinition = "varchar(255) default 'classic'")
    private String typeCard;

    @Column(name = "balance", nullable = false, columnDefinition = "decimal default 0.0")
    private BigDecimal balance;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private boolean status;

    @Column(name = "vip", nullable = false, columnDefinition = "boolean default false")
    private boolean vip;

    public BigDecimal depositMoney(BigDecimal amount) {
        return this.balance.add(amount);
    }

    public BigDecimal withdrawMoney(BigDecimal amount) {
        return this.balance.subtract(amount);
    }

    public void changeStatus() {
        this.status = !this.status;
    }

    public void changeVip() {
        this.vip = !this.vip;
    }

    public BigDecimal transferMoney(Card card, BigDecimal amount) {
        this.balance.subtract(amount);
        return card.balance.add(amount);
    }

    public BigDecimal pay(Card card, BigDecimal amount) {
        this.balance.subtract(amount);
        return card.balance.add(amount);
    }

}
