package ru.sigmaton.moneyhelper.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.exception.BudgetNotExists;
import ru.sigmaton.moneyhelper.model.Budget;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final AccountDetailsService accountService;


    public Budget getBudget(Principal principal) {
        var account = accountService.findByLogin(principal.getName());

        return account.getBudget();
    }

}
