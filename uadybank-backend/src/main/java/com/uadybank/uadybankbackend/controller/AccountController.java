package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.dto.AccountDTO;
import com.uadybank.uadybankbackend.dto.CardDTO;
import com.uadybank.uadybankbackend.entity.Account;
import com.uadybank.uadybankbackend.entity.Card;
import com.uadybank.uadybankbackend.mapper.AccountMapper;
import com.uadybank.uadybankbackend.mapper.CardMapper;
import com.uadybank.uadybankbackend.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for account operations.
 */
@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AccountController implements iController<Account> {

    private final AccountService service;

    /**
     * Constructor to inject the account service.
     *
     * @param service the account service
     */
    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    /**
     * Gets all accounts.
     *
     * @param idEmployee the employee ID
     * @return a list of accounts
     */
    @GetMapping
    public ResponseEntity<?> getAll(@CookieValue(value = "administrator", required = false) Long idEmployee) {
        if (idEmployee == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized access");
        }

        List<Account> accounts = service.getAll();
        List<AccountDTO> accountsDTO = accounts.stream()
                .map(AccountMapper::mapToDTO)
                .toList();
        return ResponseEntity.ok(accountsDTO);
    }

    /**
     * Gets an account by its ID.
     *
     * @param id the account ID
     * @return the account
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Account account = service.getById(id);
        AccountDTO accountDTO = AccountMapper.mapToDTO(account);
        return ResponseEntity.ok(accountDTO);
    }

    /**
     * Gets an account by its matricula.
     *
     * @param matricula the matricula
     * @return the account
     */
    @GetMapping("/client")
    public ResponseEntity<?> getByMatricula(@CookieValue("client") String matricula) {
        Optional<Account> account = service.getByMatricula(matricula);
        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AccountDTO accountDTO = AccountMapper.mapToDTO(account.get());
        return ResponseEntity.ok(accountDTO);
    }

    /**
     * Creates a new account.
     *
     * @param account the account to create
     * @return the created account
     */
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Account account) {
        Account newAccount = service.save(account);
        AccountDTO accountDTO = AccountMapper.mapToDTO(newAccount);
        return ResponseEntity.ok(accountDTO);
    }

    /**
     * Updates an account.
     *
     * @param id the account ID
     * @param account the account with the updated data
     * @return the updated account
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid@RequestBody Account account) {
        Account newAccount = service.update(id, account);
        AccountDTO accountDTO = AccountMapper.mapToDTO(newAccount);
        return ResponseEntity.ok(accountDTO);
    }

    /**
     * Deletes an account.
     *
     * @param id the account ID
     * @return a response entity with a success message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Account deleted");
    }

    /**
     * Gets all cards of an account.
     *
     * @param id the account ID
     * @return a list of cards of the account
     */
    @GetMapping("/{id}/cards")
    public ResponseEntity<?> getCards(@PathVariable Long id) {
        List<Card> cards = service.getCards(id);
        List<CardDTO> cardsDTO = cards.stream()
                .map(CardMapper::mapToDTO)
                .toList();
        return ResponseEntity.ok(cardsDTO);
    }

    /**
     * Gets a card of an account by its ID.
     *
     * @param id the account ID
     * @param cardId the card ID
     * @return the card
     */
    @GetMapping("/{id}/cards/{cardId}")
    public ResponseEntity<?> getCard(@PathVariable Long id, @PathVariable String cardId) {
        Card card = service.getCard(id, cardId);
        CardDTO cardDTO = CardMapper.mapToDTO(card);
        return ResponseEntity.ok(cardDTO);
    }

    /**
     * Creates a new card for an account.
     *
     * @param id the account ID
     * @param card the card to create
     * @return the created card
     */
    @PostMapping("/{id}/cards")
    public ResponseEntity<?> createCard(@PathVariable Long id, @Valid @RequestBody Card card) {
        service.addCard(id, card);
        CardDTO cardDTO = CardMapper.mapToDTO(card);
        return ResponseEntity.ok(cardDTO);
    }

    /**
     * Deletes a card of an account.
     *
     * @param id the account ID
     * @param cardId the card ID
     * @return a response entity with a success message
     */
    @DeleteMapping("/{id}/cards/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id, @PathVariable String cardId) {
        service.deleteCard(id, cardId);
        return ResponseEntity.ok("Card deleted");
    }

}