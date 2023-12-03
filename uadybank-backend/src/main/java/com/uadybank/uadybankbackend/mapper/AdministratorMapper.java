package com.uadybank.uadybankbackend.mapper;

import com.uadybank.uadybankbackend.dto.AdministratorDTO;
import com.uadybank.uadybankbackend.entity.Administrator;

public class AdministratorMapper {

    public static AdministratorDTO mapToDTO(Administrator administrator) {
        AdministratorDTO dto = new AdministratorDTO();
        dto.setRole("administrator");
        dto.setCreationDate(administrator.getCreationDate());
        dto.setIdEmployee(administrator.getIdEmployee());
        dto.setName(administrator.getName());
        dto.setEmail(administrator.getEmail());
        dto.setPassword(administrator.getPassword());
        dto.setPhoneNumber(administrator.getPhoneNumber());
        dto.setVerified(administrator.isVerified());
        return dto;
    }

}
