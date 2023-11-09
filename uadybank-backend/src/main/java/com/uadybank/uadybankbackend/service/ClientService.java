package com.uadybank.uadybankbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private ClientService repository;

    @Autowired
    public void setRepository(ClientService repository) {
        this.repository = repository;
    }

}
