package com.uadybank.uadybankbackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clients")
public class Client extends User {

    @Id
    @Column(name = "matricula", nullable = false, length = 8)
    private String matricula;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonBackReference
    private Account account;

    @Column(name = "address", nullable = false)
    @NotBlank
    private String address;

}
