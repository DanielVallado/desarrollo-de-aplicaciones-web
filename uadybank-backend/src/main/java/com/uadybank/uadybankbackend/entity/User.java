package com.uadybank.uadybankbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "email", nullable = false)
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false)
    @NotBlank
    private String password;

    @Column(name = "phoneNumber", nullable = false)
    @NotBlank
    private String phoneNumber;

    @Column(name = "isVerified", nullable = false, columnDefinition = "boolean default false")
    @NotBlank
    private boolean isVerified;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    @NotBlank
    private boolean status;

}
