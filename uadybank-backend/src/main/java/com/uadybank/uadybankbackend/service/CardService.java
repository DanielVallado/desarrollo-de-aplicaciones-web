package com.uadybank.uadybankbackend.service;

import com.uadybank.uadybankbackend.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    private CardRepository repository;

    @Autowired
    public void setRepository(CardRepository repository) {
        this.repository = repository;
    }

}
