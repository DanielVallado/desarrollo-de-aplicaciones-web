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

@RestController
@RequestMapping("/account")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AccountController implements iController<Account> {

    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Account account = service.getById(id);
        AccountDTO accountDTO = AccountMapper.mapToDTO(account);
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("/client")
    public ResponseEntity<?> getByMatricula(@CookieValue("client") String matricula) {
        Optional<Account> account = service.getByMatricula(matricula);
        if (account.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AccountDTO accountDTO = AccountMapper.mapToDTO(account.get());
        return ResponseEntity.ok(accountDTO);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Account account) {
        Account newAccount = service.save(account);
        AccountDTO accountDTO = AccountMapper.mapToDTO(newAccount);
        return ResponseEntity.ok(accountDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid@RequestBody Account account) {
        Account newAccount = service.update(id, account);
        AccountDTO accountDTO = AccountMapper.mapToDTO(newAccount);
        return ResponseEntity.ok(accountDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Account deleted");
    }

    //! Cards
    @GetMapping("/{id}/cards")
    public ResponseEntity<?> getCards(@PathVariable Long id) {
        List<Card> cards = service.getCards(id);
        List<CardDTO> cardsDTO = cards.stream()
                .map(CardMapper::mapToDTO)
                .toList();
        return ResponseEntity.ok(cardsDTO);
    }

    @GetMapping("/{id}/cards/{cardId}")
    public ResponseEntity<?> getCard(@PathVariable Long id, @PathVariable String cardId) {
        Card card = service.getCard(id, cardId);
        CardDTO cardDTO = CardMapper.mapToDTO(card);
        return ResponseEntity.ok(cardDTO);
    }

    @PostMapping("/{id}/cards")
    public ResponseEntity<?> createCard(@PathVariable Long id, @Valid @RequestBody Card card) {
        service.addCard(id, card);
        CardDTO cardDTO = CardMapper.mapToDTO(card);
        return ResponseEntity.ok(cardDTO);
    }

    @DeleteMapping("/{id}/cards/{cardId}")
    public ResponseEntity<?> deleteCard(@PathVariable Long id, @PathVariable String cardId) {
        service.deleteCard(id, cardId);
        return ResponseEntity.ok("Card deleted");
    }

}