package com.uadybank.uadybankbackend.service;

import com.uadybank.uadybankbackend.entity.Client;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    public Client authenticate(String email, String password) {
        System.out.println(email);
        System.out.println(password);
        return null;
    }

}
