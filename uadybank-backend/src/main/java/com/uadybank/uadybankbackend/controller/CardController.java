package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.dto.CardDTO;
import com.uadybank.uadybankbackend.dto.TransactionDTO;
import com.uadybank.uadybankbackend.entity.Card;
import com.uadybank.uadybankbackend.entity.Transaction;
import com.uadybank.uadybankbackend.mapper.CardMapper;
import com.uadybank.uadybankbackend.mapper.TransactionMapper;
import com.uadybank.uadybankbackend.service.CardService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class CardController {

    private final CardService service;

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@CookieValue(value = "administrator", required = false) Long idEmployee) {
        if (idEmployee == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized access");
        }

        List<Card> cards = service.getAll();
        List<CardDTO> cardsDTO = cards.stream()
                .map(CardMapper::mapToDTO)
                .toList();
        return ResponseEntity.ok(cardsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Card card = service.getById(id);
        CardDTO cardDTO = CardMapper.mapToDTO(card);
        return ResponseEntity.ok(cardDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid@RequestBody Card card) {
        Card newCard = service.update(id, card);
        CardDTO cardDTO = CardMapper.mapToDTO(newCard);
        return ResponseEntity.ok(cardDTO);
    }

    @GetMapping("/{id}/transactions")
    public ResponseEntity<?> getTransactions(@PathVariable String id) {
        List<Transaction> transactions = service.getTransactions(id);
        List<TransactionDTO> transactionsDTO = transactions.stream()
                .map(TransactionMapper::mapToDTO)
                .toList();
        return ResponseEntity.ok(transactionsDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createTransaction(@PathVariable String id, @Valid@RequestBody Transaction transaction) {
        Card newCard = service.performTransaction(id, transaction);
        CardDTO cardDTO = CardMapper.mapToDTO(newCard);
        return ResponseEntity.ok(cardDTO);
    }

}