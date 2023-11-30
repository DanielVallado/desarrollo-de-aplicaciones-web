package com.uadybank.uadybankbackend.mapper;

import com.uadybank.uadybankbackend.dto.TransactionDTO;
import com.uadybank.uadybankbackend.entity.Transaction;

public class TransactionMapper {

    public static TransactionDTO mapToDTO(Transaction transaction) {
        TransactionDTO dto = new TransactionDTO();
        dto.setIdTransaction(transaction.getIdTransaction());
        dto.setAmount(transaction.getAmount());
        dto.setDescription(transaction.getDescription());
        dto.setTransactionType(transaction.getTransactionType().name());
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setDestinationCard(transaction.getDestination());
        return dto;
    }

}
