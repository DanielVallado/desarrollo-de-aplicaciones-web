package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.entity.Client;
import com.uadybank.uadybankbackend.exception.ResourceNotFoundException;
import com.uadybank.uadybankbackend.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientRepository repository;

    @Autowired
    public void setRepository(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/clients")
    public ResponseEntity<?> listarTodosClients() {
        List<Client> clients = repository.findAll();
        return ResponseEntity.ok(clients);
    }

    @GetMapping("/clients/{id}")
    public ResponseEntity<?> obtenerClientPorId(@PathVariable Long id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID " + id));
        return ResponseEntity.ok(client);
    }

    @PostMapping("/clients")
    public ResponseEntity<?> guardarClient(@Valid @RequestBody Client client) {
        repository.save(client);
        return ResponseEntity.ok(client);
    }

    @PutMapping("/clients/{id}")
    public ResponseEntity<?> actualizarClient(@PathVariable Long id, @Valid@RequestBody Client client) {
        Client clientExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        clientExistente.setName(client.getName());
        clientExistente.setEmail(client.getEmail());
        clientExistente.setPassword(client.getPassword());
        clientExistente.setPhoneNumber(client.getPhoneNumber());
        clientExistente.setVerified(client.isVerified());
        clientExistente.setStatus(client.isStatus());
        clientExistente.setMatricula(client.getMatricula());
        clientExistente.setAddress(client.getAddress());

        Client newClient = repository.save(clientExistente);

        return ResponseEntity.ok(newClient);
    }

    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> eliminarClient(@PathVariable Long id) {
        Client clientExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con ID: " + id));

        repository.delete(clientExistente);
        return ResponseEntity.ok("Cliente eliminado");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}