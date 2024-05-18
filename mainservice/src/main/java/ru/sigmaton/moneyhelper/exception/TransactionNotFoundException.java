package ru.sigmaton.moneyhelper.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class TransactionNotFoundException extends ApiException {

    public TransactionNotFoundException(Long transactionId) {
        super("Transaction not found with id %s".formatted(transactionId));
    }

}
