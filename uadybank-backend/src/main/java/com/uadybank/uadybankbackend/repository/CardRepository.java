package com.uadybank.uadybankbackend.repository;

import com.uadybank.uadybankbackend.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, String> {

    boolean existsByIdCard(String idCard);

}
