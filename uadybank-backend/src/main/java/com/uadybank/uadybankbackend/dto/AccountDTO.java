package com.uadybank.uadybankbackend.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object for Account.
 * This class is used to transfer account data between different parts of the application.
 */
@Data
@NoArgsConstructor
public class AccountDTO {

    /**
     * The ID of the account.
     */
    private Long idAccount;

    /**
     * The client associated with the account.
     */
    private ClientDTO client;

    /**
     * The list of cards associated with the account.
     */
    private List<CardDTO> cards;

}
