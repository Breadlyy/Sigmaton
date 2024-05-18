package ru.sigmaton.moneyhelper.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class CategoryNotFoundException extends ApiException {
    public CategoryNotFoundException(Long id) {
        super("Category with id=%s not found.".formatted(id));
    }
}
