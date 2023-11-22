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
public class AccountController {
    private AccountRepository repository;

    @Autowired
    public void setRepository(AccountRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/accounts")
    public ResponseEntity<?> listarTodosAccounts() {
        List<Account> accounts = repository.findAll();
        return ResponseEntity.ok(accounts);
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<?> obtenerAccountPorId(@PathVariable Long id) {
        Account account = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID " + id));
        return ResponseEntity.ok(account);
    }

    @PostMapping("/accounts")
    public ResponseEntity<?> guardarAccount(@Valid @RequestBody Account account) {
        repository.save(account);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/accounts/{id}")
    public ResponseEntity<?> actualizarAccount(@PathVariable Long id, @Valid@RequestBody Account account) {
        Account accountExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con ID: " + id));

        accountExistente.setClient(account.getClient());
        accountExistente.setCards(account.getCards());

        Account newAccount = repository.save(accountExistente);

        return ResponseEntity.ok(newAccount);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity<?> eliminarAccount(@PathVariable Long id) {
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
