package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.dto.ClientDTO;
import com.uadybank.uadybankbackend.entity.Client;
import com.uadybank.uadybankbackend.mapper.ClientMapper;
import com.uadybank.uadybankbackend.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for client operations.
 */
@RestController
@RequestMapping("/client")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ClientController {

    private final ClientService service;

    /**
     * Constructor to inject the client service.
     *
     * @param service the client service
     */
    @Autowired
    public ClientController(ClientService service) {
        this.service = service;
    }

    /**
     * Gets all clients.
     *
     * @param idEmployee the employee ID
     * @return a list of clients
     */
    @GetMapping
    public ResponseEntity<?> getAll(@CookieValue(value = "administrator", required = false) Long idEmployee) {
        if (idEmployee == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized access");
        }

        List<Client> clients = service.getAll();
        List<ClientDTO> clientsDTO = clients.stream()
                .map(ClientMapper::mapToDTO)
                .toList();

        return ResponseEntity.ok(clientsDTO);
    }

    /**
     * Gets a client by its ID.
     *
     * @param id the client ID
     * @return the client
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Client client = service.getById(id);
        ClientDTO clientDTO = ClientMapper.mapToDTO(client);
        return ResponseEntity.ok(clientDTO);
    }

    /**
     * Updates a client.
     *
     * @param id the client ID
     * @param client the client with the updated data
     * @return the updated client
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid@RequestBody Client client) {
        Client newClient = service.update(id, client);
        ClientDTO clientDTO = ClientMapper.mapToDTO(newClient);
        return ResponseEntity.ok(clientDTO);
    }

    /**
     * Verifies a client.
     *
     * @param id the client ID
     * @return a response entity with a success message
     */
    @GetMapping("/{id}/verify")
    public ResponseEntity<?> verify(@PathVariable String id) {
        service.verify(id);
        return ResponseEntity.ok("Client verified");
    }

}