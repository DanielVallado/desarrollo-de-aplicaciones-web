package com.uadybank.uadybankbackend.factory;

import com.uadybank.uadybankbackend.entity.Card;

public class SilverCardFactory implements CardFactory {

    @Override
    public Card createCard() {
        return new Card();
    }

}
