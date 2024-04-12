package com.uadybank.uadybankbackend.controller;

import com.uadybank.uadybankbackend.dto.AdministratorDTO;
import com.uadybank.uadybankbackend.entity.Administrator;
import com.uadybank.uadybankbackend.mapper.AdministratorMapper;
import com.uadybank.uadybankbackend.service.AdministratorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for administrator operations.
 */
@RestController
@RequestMapping("/administrator")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class AdministratorController implements iController<Administrator> {

    private final AdministratorService service;

    /**
     * Constructor to inject the administrator service.
     *
     * @param service the administrator service
     */
    @Autowired
    public AdministratorController(AdministratorService service) {
        this.service = service;
    }

    /**
     * Gets all administrators.
     *
     * @param idEmployee the employee ID
     * @return a list of administrators
     */
    @GetMapping
    public ResponseEntity<?> getAll(@CookieValue(value = "administrator", required = false) Long idEmployee) {
        if (idEmployee == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Unauthorized access");
        }

        List<Administrator> administrators = service.getAll();
        List<AdministratorDTO> administratorDTOs = administrators.stream()
                .map(AdministratorMapper::mapToDTO)
                .toList();
        return ResponseEntity.ok(administratorDTOs);
    }

    /**
     * Gets an administrator by its ID.
     *
     * @param id the administrator ID
     * @return the administrator
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        Administrator administrator = service.getById(id);
        AdministratorDTO administratorDTO = AdministratorMapper.mapToDTO(administrator);
        return ResponseEntity.ok(administratorDTO);
    }

    /**
     * Creates a new administrator.
     *
     * @param administrator the administrator to create
     * @return the created administrator
     */
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Administrator administrator) {
        Administrator newAdministrator = service.save(administrator);
        AdministratorDTO administratorDTO = AdministratorMapper.mapToDTO(newAdministrator);
        return ResponseEntity.ok(administratorDTO);
    }

    /**
     * Updates an administrator.
     *
     * @param id the administrator ID
     * @param administrator the administrator with the updated data
     * @return the updated administrator
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid@RequestBody Administrator administrator) {
        Administrator newAdministrator = service.update(id, administrator);
        AdministratorDTO administratorDTO = AdministratorMapper.mapToDTO(newAdministrator);
        return ResponseEntity.ok(administratorDTO);
    }

    /**
     * Deletes an administrator.
     *
     * @param id the administrator ID
     * @return a response entity with a success message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Administrator deleted");
    }

}