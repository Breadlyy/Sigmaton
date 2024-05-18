package ru.sigmaton.moneyhelper.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class BudgetNotExists extends ApiException {

    public BudgetNotExists() {
        super("Budget not exist.");
    }

}
