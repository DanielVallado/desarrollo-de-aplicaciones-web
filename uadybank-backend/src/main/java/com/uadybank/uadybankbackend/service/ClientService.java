package com.uadybank.uadybankbackend.service;

import com.uadybank.uadybankbackend.entity.Client;
import com.uadybank.uadybankbackend.exception.ResourceNotFoundException;
import com.uadybank.uadybankbackend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository repository;

    @Autowired
    public ClientService(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getAll() {
        List<Client> clients = repository.findAll();
        if (clients.isEmpty()) {
            throw new ResourceNotFoundException("Clients not found");
        }
        return clients.stream()
                .filter(Client::getStatus)
                .collect(Collectors.toList());
    }

    public Client getById(String id) {
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ResourceNotFoundException("Client not found for ID: " + id));
    }

    public Optional<Client> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public Client save(Client client) {
        Optional<Client> existingClient = repository.findByEmail(client.getEmail());
        if (existingClient.isPresent()) {
            throw new IllegalArgumentException("A client with the email " + client.getEmail() + " already exists");
        }
        client.setVerified(false);
        client.setStatus(true);
        return repository.save(client);
    }

    public Client update(String id, Client client) {
        Client clientToUpdate = this.getById(id);

        clientToUpdate.update(client);
        Optional.ofNullable(client.getMatricula()).filter(m -> !m.isEmpty()).ifPresent(clientToUpdate::setMatricula);
        Optional.ofNullable(client.getAddress()).filter(a -> !a.isEmpty()).ifPresent(clientToUpdate::setAddress);

        return repository.save(clientToUpdate);
    }

    public void delete(String id)  {
        Client client = this.getById(id);
        client.setStatus(false);
        repository.save(client);
    }

    public void verify(String id) {
        Client client = this.getById(id);
        client.setVerified(true);
        repository.save(client);
    }

}
