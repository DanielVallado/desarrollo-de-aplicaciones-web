package com.uadybank.uadybankbackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Data
@MappedSuperclass
public class User {

    @Column(name = "creation_date", nullable = false, columnDefinition = "datetime default now()")
    @NotNull
    private LocalDateTime creationDate;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    @Column(name = "email", nullable = false)
    @NotBlank
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    @NotBlank
    private String password;

    @Column(name = "phone_number", nullable = false, length = 15)
    @NotBlank
    private String phoneNumber;

    @Column(name = "verified", nullable = false, columnDefinition = "boolean default false")
    @NotNull
    private boolean verified;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private boolean status;

    public boolean getStatus() {
        return status;
    }

    @PrePersist
    private void setCreationDate() {
        this.creationDate = LocalDateTime.now();
    }

    public String getCreationDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return this.creationDate.format(formatter);
    }

    public void update(User user) {
        Optional.ofNullable(user.getName()).filter(n -> !n.isEmpty()).ifPresent(this::setName);
        Optional.ofNullable(user.getEmail()).filter(e -> !e.isEmpty()).ifPresent(this::setEmail);
        Optional.ofNullable(user.getPassword()).filter(p -> !p.isEmpty()).ifPresent(this::setPassword);
        Optional.ofNullable(user.getPhoneNumber()).filter(pn -> !pn.isEmpty()).ifPresent(this::setPhoneNumber);
    }

}
