package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.entity.Administrator;
import com.uadybank.uadybankbackend.exception.ResourceNotFoundException;
import com.uadybank.uadybankbackend.repository.AdministratorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrator")
public class AdministratorController implements iController<Administrator> {

    private AdministratorRepository repository;

    @Autowired
    public void setRepository(AdministratorRepository repository) {
        this.repository = repository;
    }

    @Override
    @GetMapping("/administrators")
    public ResponseEntity<?> getAll() {
        List<Administrator> administrators = repository.findAll();
        return ResponseEntity.ok(administrators);
    }

    @Override
    @GetMapping("/administrators/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Administrator administrator = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado con ID " + id));
        return ResponseEntity.ok(administrator);
    }

    @Override
    @PostMapping("/administrators")
    public ResponseEntity<?> save(@Valid @RequestBody Administrator administrator) {
        repository.save(administrator);
        return ResponseEntity.ok(administrator);
    }

    @Override
    @PutMapping("/administrators/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Administrator administrator) {
        Administrator administratorExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado con ID: " + id));

        administratorExistente.setName(administrator.getName());
        administratorExistente.setEmail(administrator.getEmail());
        administratorExistente.setPassword(administrator.getPassword());
        administratorExistente.setPhoneNumber(administrator.getPhoneNumber());
        administratorExistente.setVerified(administrator.isVerified());
        administratorExistente.setStatus(administrator.isStatus());

        Administrator newAdministrator = repository.save(administratorExistente);

        return ResponseEntity.ok(newAdministrator);
    }

    @Override
    @DeleteMapping("/administrators/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Administrator administratorExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado con ID: " + id));

        repository.delete(administratorExistente);
        return ResponseEntity.ok("Administrador eliminado");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}