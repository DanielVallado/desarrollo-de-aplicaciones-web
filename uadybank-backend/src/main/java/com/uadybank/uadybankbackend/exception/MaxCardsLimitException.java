package com.uadybank.uadybankbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MaxCardsLimitException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public MaxCardsLimitException(String message) {
        super(message);
    }

}
