package com.uadybank.uadybankbackend.service;

import com.uadybank.uadybankbackend.entity.Administrator;
import com.uadybank.uadybankbackend.exception.ResourceNotFoundException;
import com.uadybank.uadybankbackend.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService implements iService<Administrator> {

    private final AdministratorRepository repository;

    @Autowired
    public AdministratorService(AdministratorRepository repository) {
        this.repository = repository;
    }

    public List<Administrator> getAll() {
        List<Administrator> administrators = repository.findAll();
        if (administrators.isEmpty()) {
            throw new ResourceNotFoundException("Administrators not found");
        }
        return administrators;
    }

    public Administrator getById(Long id) {
        Optional<Administrator> administrator = repository.findById(id);
        return administrator.orElseThrow(() -> new ResourceNotFoundException("Administrator not found for ID: " + id));
    }

    public Optional<Administrator> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Administrator save(Administrator administrator) {
        administrator.setVerified(false);
        administrator.setStatus(true);
        return repository.save(administrator);
    }

    public Administrator update(Long id, Administrator administrator) {
        Administrator administratorToUpdate = getById(id);
        administratorToUpdate.update(administrator);
        return repository.save(administratorToUpdate);
    }

    public void delete(Long id)  {
        Administrator administrator = this.getById(id);
        administrator.setStatus(false);
        repository.save(administrator);
    }

}
