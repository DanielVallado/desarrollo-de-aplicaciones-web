package com.uadybank.uadybankbackend.repository;

import com.uadybank.uadybankbackend.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findByEmail(String email);

}
