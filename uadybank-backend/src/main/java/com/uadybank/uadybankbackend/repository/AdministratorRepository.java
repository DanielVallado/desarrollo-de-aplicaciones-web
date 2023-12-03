package com.uadybank.uadybankbackend.repository;

import com.uadybank.uadybankbackend.entity.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    Optional<Administrator> findByEmail(String email);

}
