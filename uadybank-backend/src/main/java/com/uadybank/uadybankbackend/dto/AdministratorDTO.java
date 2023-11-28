package com.uadybank.uadybankbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdministratorDTO {

    private Long idEmployee;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private boolean isVerified;

}
