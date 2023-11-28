package com.uadybank.uadybankbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import java.util.List;

public record ValidationErrorResponse(HttpStatus status, String message, List<FieldError> errors) {

}
