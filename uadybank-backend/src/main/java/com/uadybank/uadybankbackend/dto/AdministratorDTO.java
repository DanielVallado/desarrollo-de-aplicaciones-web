package com.uadybank.uadybankbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AdministratorDTO {

    private String role;
    private String creationDate;
    private Long idEmployee;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isVerified;

}
