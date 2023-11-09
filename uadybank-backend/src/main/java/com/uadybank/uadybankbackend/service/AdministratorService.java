package com.uadybank.uadybankbackend.service;

import com.uadybank.uadybankbackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository repository;

    @Autowired
    public void setRepository(AccountRepository repository) {
        this.repository = repository;
    }

}
