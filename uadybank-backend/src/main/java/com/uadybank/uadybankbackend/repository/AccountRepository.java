package com.uadybank.uadybankbackend.repository;

import com.uadybank.uadybankbackend.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
