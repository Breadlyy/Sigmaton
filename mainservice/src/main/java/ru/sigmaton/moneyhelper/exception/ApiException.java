package ru.sigmaton.moneyhelper.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}
