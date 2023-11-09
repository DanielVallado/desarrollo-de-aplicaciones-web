package com.uadybank.uadybankbackend.service;

import com.uadybank.uadybankbackend.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministratorService {

    private AdministratorRepository repository;

    @Autowired
    public void setRepository(AdministratorRepository repository) {
        this.repository = repository;
    }

}
