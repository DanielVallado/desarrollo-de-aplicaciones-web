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

/**
 * Controller for login operations.
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class LoginController {

    private final LoginService service;

    /**
     * Constructor to inject the login service.
     *
     * @param service the login service
     */
    @Autowired
    public LoginController(LoginService service) {
        this.service = service;
    }

    /**
     * Authenticates a user.
     *
     * @param loginDTO the login data
     * @param response the HTTP response
     * @return the authenticated user
     */
    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO, HttpServletResponse response) {
        Client client = service.authenticate(loginDTO.getEmail(), loginDTO.getPassword());
        if (client != null) {
            Cookie cookie = new Cookie("client", client.getMatricula());
            cookie.setMaxAge(2 * 60 * 60); // 2 horas
            response.addCookie(cookie);
            return ResponseEntity.ok(ClientMapper.mapToDTO(client));
        }

        Administrator administrator = service.authenticateAdmin(loginDTO.getEmail(), loginDTO.getPassword());
        if (administrator != null) {
            Cookie cookie = new Cookie("administrator", administrator.getIdEmployee().toString());
            cookie.setMaxAge(2 * 60 * 60);
            response.addCookie(cookie);
            return ResponseEntity.ok(AdministratorMapper.mapToDTO(administrator));
        }

        ErrorDTO errorDTO = new ErrorDTO("Error", "Invalid credentials");
        return ResponseEntity.ok(errorDTO);
    }

    /**
     * Logs out a user.
     *
     * @param response the HTTP response
     * @return a response entity with a success message
     */
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie clientCookie = new Cookie("client", ""); //borra el valor de la clientCookie
        clientCookie.setMaxAge(0); //establece su tiempo de vida a 0
        response.addCookie(clientCookie);

        Cookie adminCookie = new Cookie("administrator", "");
        adminCookie.setMaxAge(0);
        response.addCookie(adminCookie);
    return ResponseEntity.ok().build();
}

}
