package com.uadybank.uadybankbackend.dto;

import com.uadybank.uadybankbackend.entity.Client;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AccountDTO {

    private Long idAccount;
    private Client client;
    private List<CardDTO> cards;

}
