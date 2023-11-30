package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.dto.LoginDTO;
import com.uadybank.uadybankbackend.entity.Client;
import com.uadybank.uadybankbackend.mapper.ClientMapper;
import com.uadybank.uadybankbackend.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService service;

    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO) {
        Client client = service.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
        if (client != null) {
            return ResponseEntity.ok(ClientMapper.mapToDTO(client));
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}
