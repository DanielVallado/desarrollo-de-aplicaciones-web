package com.uadybank.uadybankbackend.mapper;

import com.uadybank.uadybankbackend.dto.ClientDTO;
import com.uadybank.uadybankbackend.entity.Client;

public class ClientMapper {

    public static ClientDTO mapToDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setRole("client");
        dto.setCreationDate(client.getCreationDate());
        dto.setMatricula(client.getMatricula());
        dto.setAddress(client.getAddress());
        dto.setName(client.getName());
        dto.setEmail(client.getEmail());
        dto.setPassword(client.getPassword());
        dto.setPhoneNumber(client.getPhoneNumber());
        dto.setVerified(client.isVerified());
        return dto;
    }

}
