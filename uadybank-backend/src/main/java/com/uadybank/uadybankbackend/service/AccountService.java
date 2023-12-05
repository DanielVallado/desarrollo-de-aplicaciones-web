package com.uadybank.uadybankbackend.service;

import com.uadybank.uadybankbackend.Util.CardNumberGeneratorUtil;
import com.uadybank.uadybankbackend.entity.Account;
import com.uadybank.uadybankbackend.entity.Card;
import com.uadybank.uadybankbackend.entity.Client;
import com.uadybank.uadybankbackend.exception.MaxCardsLimitException;
import com.uadybank.uadybankbackend.exception.ResourceNotFoundException;
import com.uadybank.uadybankbackend.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountService implements iService<Account> {

    private final AccountRepository repository;
    private final ClientService clientService;
    private final CardService cardService;

    public AccountService(AccountRepository repository, ClientService clientService, CardService cardService) {
        this.repository = repository;
        this.clientService = clientService;
        this.cardService = cardService;
    }

    public List<Account> getAll() {
        List<Account> accounts = repository.findAll();
        if (accounts.isEmpty()) {
            throw new ResourceNotFoundException("Accounts not found");
        }
        return accounts;
    }

    public Account getById(Long id) {
        Optional<Account> account = repository.findById(id);
        return account.orElseThrow(() -> new ResourceNotFoundException("Account not found for ID: " + id));
    }

    public Optional<Account> getByMatricula(String matricula) {
        return repository.findByClient_Matricula(matricula);
    }

    public Account save(Account account) {
        Client client = account.getClient();
        if (clientService.getByEmail(client.getEmail()).isPresent()) {
            throw new IllegalArgumentException("A client with the email " + client.getEmail() + " already exists");
        }
        client.setStatus(true);

        List<Card> cards = account.getCards();
        for (Card card : cards) {
            String cardNumber;
            do {
                cardNumber = CardNumberGeneratorUtil.generateCardNumber();
            } while (cardService.existsByIdCard(cardNumber));

            card.setIdCard(cardNumber);
            card.setStatus(true);
            card.setVip(false);
        }

        account.setCards(cards);
        return repository.save(account);
    }

    public Account update(Long id, Account account) {
        Account accountToUpdate = getById(id);

        Optional.ofNullable(account.getClient()).ifPresent(accountToUpdate::setClient);
        Optional.ofNullable(account.getCards()).ifPresent(accountToUpdate::setCards);

        return repository.save(accountToUpdate);
    }

    public void delete(Long id)  {
        Account account = this.getById(id);
        // Delete client and cards
        clientService.delete(account.getClient().getMatricula());
        for (Card card : account.getCards()) {
            cardService.delete(card.getIdCard());
        }
        // Delete account
        account.setStatus(false);
        repository.save(account);
    }

    public List<Card> getCards(Long id) {
        Account account = getById(id);
        return account.getCards().stream()
                .filter(Card::getStatus)
                .collect(Collectors.toList());
    }

    public Card getCard(Long id, String idCard) {
        Account account = getById(id);
        return account.getCards().stream()
                .filter(card -> card.getIdCard().equals(idCard) && card.getStatus())
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Card not found or not active for ID: " + idCard));
    }

    public void addCard(Long id, Card card) {
        Account account = getById(id);
        long activeCardsCount = account.getCards().stream()
                .filter(Card::getStatus)
                .count();
        if (activeCardsCount < 3) {
            String cardNumber;
            do {
                cardNumber = CardNumberGeneratorUtil.generateCardNumber();
            } while (cardService.existsByIdCard(cardNumber));

            card.setIdCard(cardNumber);
            card.setStatus(true);
            card.setVip(false);
            account.getCards().add(card);
            repository.save(account);
        } else {
            throw new MaxCardsLimitException("Account already has 3 cards");
        }
    }

    public void deleteCard(Long id, String idCard) {
        Account account = getById(id);
        Card card = account.getCard(idCard);
        card.setStatus(false);
        repository.save(account);
    }

}
