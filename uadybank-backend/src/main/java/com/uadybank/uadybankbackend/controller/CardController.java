package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.entity.Card;
import com.uadybank.uadybankbackend.exception.ResourceNotFoundException;
import com.uadybank.uadybankbackend.repository.CardRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
public class CardController implements iController<Card> {

    private CardRepository repository;

    @Autowired
    public void setRepository(CardRepository repository) {
        this.repository = repository;
    }

    @Override
    @GetMapping("/cards")
    public ResponseEntity<?> getAll() {
        List<Card> cards = repository.findAll();
        return ResponseEntity.ok(cards);
    }

    @Override
    @GetMapping("/cards/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Card card = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarjeta no encontrada con ID " + id));
        return ResponseEntity.ok(card);
    }

    @Override
    @PostMapping("/cards")
    public ResponseEntity<?> save(@Valid @RequestBody Card card) {
        repository.save(card);
        return ResponseEntity.ok(card);
    }

    @Override
    @PutMapping("/cards/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Card card) {
        Card cardExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarjeta no encontrada con ID: " + id));

        cardExistente.setTypeCard(card.getTypeCard());
        cardExistente.setBalance(card.getBalance());
        cardExistente.setStatus(card.isStatus());
        cardExistente.setVip(card.isVip());

        Card newCard = repository.save(cardExistente);

        return ResponseEntity.ok(newCard);
    }

    @Override
    @DeleteMapping("/cards/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Card cardExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tarjeta no encontrada con ID: " + id));

        repository.delete(cardExistente);
        return ResponseEntity.ok("Tarjeta eliminada");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}