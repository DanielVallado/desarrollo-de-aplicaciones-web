package com.uadybank.uadybankbackend.repository;

import com.uadybank.uadybankbackend.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByClient_Matricula(String matricula);

}
