package com.uadybank.uadybankbackend.dto;

import lombok.Data;

@Data
public class ClientDTO {

    private String role;
    private String creationDate;
    private String matricula;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private boolean isVerified;

}
