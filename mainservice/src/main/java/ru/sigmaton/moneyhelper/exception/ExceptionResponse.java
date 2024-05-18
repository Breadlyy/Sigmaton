package ru.sigmaton.moneyhelper.exception;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ExceptionResponse (
        String error,
        String message,
        LocalDateTime timestamp
) { }
