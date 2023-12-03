package com.uadybank.uadybankbackend.mapper;

import com.uadybank.uadybankbackend.dto.AccountDTO;
import com.uadybank.uadybankbackend.entity.Account;

import java.util.stream.Collectors;

public class AccountMapper {

    public static AccountDTO mapToDTO(Account account) {
        AccountDTO dto = new AccountDTO();
        dto.setIdAccount(account.getIdAccount());
        dto.setClient(ClientMapper.mapToDTO(account.getClient()));
        dto.setCards(account.getCards().stream().map(CardMapper::mapToDTO).collect(Collectors.toList()));
        return dto;
    }

}
