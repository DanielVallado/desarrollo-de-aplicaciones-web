package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.entity.Administrator;
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

public class AdministratorController {
    private AdministratorRepository repository;
    @Autowired
    public void  setRepository(AdministratorRepository repository){
        this.repository = repository;
    }
    @GetMapping("/administrators")
    public ResponseEntity<?> listarTodosAdministradores() {
        List<Administrator> administrators = repository.findAll();
        return ResponseEntity.ok(administrators);
    }

    @GetMapping("/administrators/{id}")
    public ResponseEntity<?> obtenerAdministratorPorId(@PathVariable Long id) {
        Administrator administrator = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado con ID " + id));
        return ResponseEntity.ok(administrator);
    }

    @PostMapping("/administrators")
    public ResponseEntity<?> guardarAdministrator(@Valid @RequestBody Administrator administrator) {
        repository.save(administrator);
        return ResponseEntity.ok(administrator);
    }

    @PutMapping("/administrators/{id}")
    public ResponseEntity<?> actualizarAdministrator(@PathVariable Long id, @Valid@RequestBody Administrator administrator) {
        Administrator administratorExistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado con ID: " + id));

        administratorExistente.setName(administrator.getName());
        administratorExistente.setEmail(administrator.getEmail());
        administratorExistente.setPassword(administrator.getPassword());
        administratorExistente.setPhoneNumber(administrator.getPhoneNumber());
        administratorExistente.setVerified(administrator.isVerified());
        administratorExistente.setStatus(administrator.isStatus());


        Administrator newClient = repository.save(administratorExistente);

        return ResponseEntity.ok(newClient);
    }

    @DeleteMapping("/administrators/{id}")
    public ResponseEntity<?> eliminarClient(@PathVariable Long id) {
        Administrator administratorxistente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Administrador no encontrado con ID: " + id));

        repository.delete(administratorxistente);
        return ResponseEntity.ok("Administrador eliminado");
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
