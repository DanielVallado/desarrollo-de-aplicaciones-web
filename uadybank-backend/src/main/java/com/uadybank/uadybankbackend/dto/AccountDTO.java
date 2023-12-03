package com.uadybank.uadybankbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class AccountDTO {

    private Long idAccount;
    private ClientDTO client;
    private List<CardDTO> cards;

}
