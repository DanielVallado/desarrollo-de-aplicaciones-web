package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.entity.Account;
import com.uadybank.uadybankbackend.exception.ResourceNotFoundException;
import com.uadybank.uadybankbackend.repository.AccountRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController implements iController<Account> {

    private AccountRepository repository;

    @Autowired
    public void setRepository(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    @GetMapping("/accounts")
    public ResponseEntity<?> getAll() {
        List<Account> accounts = repository.findAll();
        return ResponseEntity.ok(accounts);
    }

    @Override
    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID " + id));
        return ResponseEntity.ok(account);
    }

    @Override
    @PostMapping("/accounts")
    public ResponseEntity<?> save(@Valid @RequestBody Account account) {
        repository.save(account);
        return ResponseEntity.ok(account);
    }

    @Override
    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Account account) {
        Account accountExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID: " + id));

        accountExistente.setClient(account.getClient());
        accountExistente.setCards(account.getCards());

        Account newAccount = repository.save(accountExistente);

        return ResponseEntity.ok(newAccount);
    }

    @Override
    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Account accountExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID: " + id));

        repository.delete(accountExistente);
        return ResponseEntity.ok("Cuenta eliminada");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}