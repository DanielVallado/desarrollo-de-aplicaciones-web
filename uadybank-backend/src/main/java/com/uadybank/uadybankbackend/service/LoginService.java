package com.uadybank.uadybankbackend.service;

import com.uadybank.uadybankbackend.entity.Administrator;
import com.uadybank.uadybankbackend.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    private final ClientService clientService;
    private final AdministratorService administratorService;

    @Autowired
    public LoginService(ClientService clientService, AdministratorService administratorService) {
        this.clientService = clientService;
        this.administratorService = administratorService;
    }

    public Client authenticate(String email, String password) {
        Optional<Client> client = clientService.getByEmail(email);
        if (client.isEmpty()) {
            return null;
        }

        if (client.get().getPassword().equals(password)) {
            return client.get();
        }

        return null;
    }

    public Administrator authenticateAdmin(String email, String password) {
        Optional<Administrator> administrator = administratorService.getByEmail(email);
        if (administrator.isEmpty()) {
            return null;
        }

        if (administrator.get().getPassword().equals(password)) {
            return administrator.get();
        }

        return null;
    }

}
