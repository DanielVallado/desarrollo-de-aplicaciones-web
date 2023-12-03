package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.dto.ErrorDTO;
import com.uadybank.uadybankbackend.dto.LoginDTO;
import com.uadybank.uadybankbackend.entity.Administrator;
import com.uadybank.uadybankbackend.entity.Client;
import com.uadybank.uadybankbackend.mapper.AdministratorMapper;
import com.uadybank.uadybankbackend.mapper.ClientMapper;
import com.uadybank.uadybankbackend.service.LoginService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoginController {

    private final LoginService service;

    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        Client client = service.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
        if (client != null) {
            Cookie cookie = new Cookie("user", "client-" + client.getMatricula());
            cookie.setMaxAge(24 * 60 * 60); // 24 horas
            response.addCookie(cookie);
            return ResponseEntity.ok(ClientMapper.mapToDTO(client));
        }

        Administrator administrator = service.authenticateAdmin(loginDTO.getEmail(), loginDTO.getPassword());
        if (administrator != null) {
            Cookie cookie = new Cookie("user", "administrator-" + administrator.getIdEmployee().toString());
            cookie.setMaxAge(24 * 60 * 60);
            response.addCookie(cookie);
            return ResponseEntity.ok(AdministratorMapper.mapToDTO(administrator));
        }

        ErrorDTO errorDTO = new ErrorDTO("Error", "Invalid credentials");
        return ResponseEntity.ok(errorDTO);
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("user", ""); //borra el valor de la cookie
        cookie.setMaxAge(0); //establece su tiempo de vida a 0
        response.addCookie(cookie);
    return ResponseEntity.ok().build();
}

}
