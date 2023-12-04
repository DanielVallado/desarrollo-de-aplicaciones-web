package com.uadybank.uadybankbackend.Util;

import java.util.Random;

public class CardNumberGeneratorUtil {

    private static final int CARD_NUMBER_LENGTH = 16;

    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();

        for (int i = 0; i < CARD_NUMBER_LENGTH; i++) {
            int digit = random.nextInt(10); // genera un dígito aleatorio entre 0 y 9
            cardNumber.append(digit);
        }

        return cardNumber.toString();
    }

}
