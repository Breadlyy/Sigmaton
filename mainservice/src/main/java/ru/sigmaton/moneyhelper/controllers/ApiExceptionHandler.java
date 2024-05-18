package ru.sigmaton.moneyhelper.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.sigmaton.moneyhelper.exception.ApiException;
import ru.sigmaton.moneyhelper.exception.ExceptionResponse;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ExceptionResponse handleConflict(ApiException ex, WebRequest request) {
        return ExceptionResponse.builder()
                .timestamp(LocalDateTime.now())
                .message(ex.getMessage())
                .error(ex.toString())
                .build();
    }

}
