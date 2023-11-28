package com.uadybank.uadybankbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccount;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    @NotBlank
    private boolean status;

    @OneToOne
    private Client client;

    @OneToMany
    private List<Card> cards;

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void deleteCard(Card card) {
        this.cards.remove(card);
    }

}
