package com.uadybank.uadybankbackend.dto;

import lombok.Data;

@Data
public class ClientDTO {

    private Long idClient;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String matricula;
    private String address;
    private boolean isVerified;

}
