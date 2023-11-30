package com.uadybank.uadybankbackend.repository;

import com.uadybank.uadybankbackend.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {

}
