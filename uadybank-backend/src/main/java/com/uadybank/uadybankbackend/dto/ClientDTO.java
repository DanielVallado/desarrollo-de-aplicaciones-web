package com.uadybank.uadybankbackend.dto;

import lombok.Data;

@Data
public class ClientDTO {

    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isVerified;
    private Long idClient;
    private String matricula;
    private String address;

}
