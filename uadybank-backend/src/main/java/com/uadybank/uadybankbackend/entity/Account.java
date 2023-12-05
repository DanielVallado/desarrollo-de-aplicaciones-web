package com.uadybank.uadybankbackend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matricula", referencedColumnName = "matricula")
    @JsonManagedReference
    @NotNull
    private Client client;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @NotNull
    private List<Card> cards;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private boolean status;

    public Card getCard(String idCard) {
        for (Card card : this.cards) {
            if (card.getIdCard().equals(idCard)) {
                return card;
            }
        }
        return null;
    }

}
