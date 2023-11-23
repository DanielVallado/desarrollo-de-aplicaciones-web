package com.uadybank.uadybankbackend.service;

import com.uadybank.uadybankbackend.dto.ClientDTO;
import com.uadybank.uadybankbackend.entity.Client;
import com.uadybank.uadybankbackend.exception.ResourceNotFoundException;
import com.uadybank.uadybankbackend.mapper.ClientMapper;
import com.uadybank.uadybankbackend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private ClientRepository repository;

    @Autowired
    public void setRepository(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> getAllClients() throws Exception {
        List<Client> clients = repository.findAll();
        if (clients.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron datos.");
        }
        return clients;
    }

    public List<ClientDTO> getAllClientsDto() throws Exception {
        List<Client> clients = repository.findAll();
        if (clients.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron datos.");
        }

        return clients.stream().map(ClientMapper::mapToDTO).collect(Collectors.toList());
    }

    public Client getClientById(Long id) throws Exception {
        Optional<Client> client = repository.findById(id);
        return client.orElseThrow(() -> new ResourceNotFoundException("No se encontraron datos"));
    }

    public ClientDTO getClientDtoById(Long id) throws Exception {
        Optional<Client> client = repository.findById(id);
        if (client.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron datos.");
        }
        return ClientMapper.mapToDTO(client.get());
    }

    public Client createClient(Client client) {
        return repository.save(client);
    }

    public void deleteClient(Long id) {
        repository.deleteById(id);
    }
}
