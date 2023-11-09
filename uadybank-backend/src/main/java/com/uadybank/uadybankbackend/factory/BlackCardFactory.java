package com.uadybank.uadybankbackend.factory;

import com.uadybank.uadybankbackend.entity.Card;

public class BlackCardFactory implements CardFactory {

    @Override
    public Card createCard() {
        return new Card();
    }

}
