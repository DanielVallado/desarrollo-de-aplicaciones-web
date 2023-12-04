package com.uadybank.uadybankbackend.service;

import com.uadybank.uadybankbackend.Util.CardNumberGeneratorUtil;
import com.uadybank.uadybankbackend.entity.Card;
import com.uadybank.uadybankbackend.entity.Transaction;
import com.uadybank.uadybankbackend.exception.ResourceNotFoundException;
import com.uadybank.uadybankbackend.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CardService {

    private final CardRepository repository;

    @Autowired
    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public List<Card> getAll() {
        List<Card> cards = repository.findAll();
        if (cards.isEmpty()) {
            throw new ResourceNotFoundException("Cards not found");
        }
        return cards;
    }

    public List<Transaction> getTransactions(String id) {
        Card card = getById(id);
        return card.getTransactions();
    }

    public Card getById(String id) {
        Optional<Card> card = repository.findById(id);
        return card.orElseThrow(() -> new ResourceNotFoundException("Card not found for ID: " + id));
    }

    public Card save(Card card) {
        String cardNumber;
        do {
            cardNumber = CardNumberGeneratorUtil.generateCardNumber();
        } while (this.existsByIdCard(cardNumber));

        card.setIdCard(cardNumber);
        card.setStatus(true);
        card.setVip(false);
        return repository.save(card);
    }

    public Card update(String id, Card card) {
        Card cardToUpdate = getById(id);

        Optional.ofNullable(card.getCardType()).ifPresent(cardToUpdate::setCardType);
        Optional.ofNullable(card.getBalance()).ifPresent(cardToUpdate::setBalance);

        return repository.save(cardToUpdate);
    }

    public void delete(String id)  {
        Card card = this.getById(id);
        card.setStatus(false);
        repository.save(card);
    }

    public boolean existsByIdCard(String idCard) {
        return repository.existsByIdCard(idCard);
    }

    public Card performTransaction(String id, Transaction transaction) {
        Card card = getById(id);
        switch (transaction.getTransactionType()) {
            case DEPOSIT -> this.depositMoney(id, transaction.getAmount());
            case WITHDRAW -> this.withdrawMoney(id, transaction.getAmount());
            case TRANSFER -> this.transferMoney(id, transaction.getDestination(), transaction.getAmount());
            case PAYMENT -> this.pay(id, transaction.getDestination(), transaction.getAmount());
            default -> throw new IllegalArgumentException("Invalid transaction type");
        }
        card.getTransactions().add(transaction);
        return repository.save(card);
    }

    public void depositMoney(String id, BigDecimal amount) {
        Card card = getById(id);
        card.setBalance(card.getBalance().add(amount));
        repository.save(card);
    }

    public void withdrawMoney(String id, BigDecimal amount) {
        Card card = getById(id);
        card.setBalance(card.getBalance().subtract(amount));
        repository.save(card);
    }

    public void transferMoney(String idFrom, String idTo, BigDecimal amount) {
        Card cardFrom = getById(idFrom);
        Card cardTo = getById(idTo);
        cardFrom.setBalance(cardFrom.getBalance().subtract(amount));
        cardTo.setBalance(cardTo.getBalance().add(amount));
        repository.save(cardFrom);
        repository.save(cardTo);
    }

    public void pay(String idFrom, String idTo, BigDecimal amount) {
        transferMoney(idFrom, idTo, amount);
    }

}