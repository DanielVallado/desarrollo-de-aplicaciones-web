package com.uadybank.uadybankbackend.mapper;

import com.uadybank.uadybankbackend.dto.CardDTO;
import com.uadybank.uadybankbackend.entity.Card;

public class CardMapper {

    public static CardDTO mapToDTO(Card card) {
        CardDTO dto = new CardDTO();
        dto.setIdCard(card.getIdCard());
        dto.setCardType(card.getCardType().name());
        dto.setBalance(card.getBalance());
        dto.setVip(card.isVip());
        return dto;
    }

}
