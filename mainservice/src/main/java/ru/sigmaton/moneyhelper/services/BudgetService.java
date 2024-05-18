package ru.sigmaton.moneyhelper.services;

import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.exception.BudgetNotExists;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.repository.BudgetRepository;

import java.security.Principal;

@Service
public class BudgetService {

    private final AccountDetailsService accountService;

    private final BudgetRepository budgetRepository;

    public BudgetService(AccountDetailsService accountService, BudgetRepository budgetRepository) {
        this.accountService = accountService;
        this.budgetRepository = budgetRepository;
    }

    public Budget getBudget(Principal principal) {
        var account = accountService.findByLogin(principal.getName());
        var budget = account.getBudget();
        if (budget == null)
            throw new BudgetNotExists();
        return budget;
    }

    public Budget createBudget(Principal principal, Long amount) {
        var account = accountService.findByLogin(principal.getName());
        var budget = Budget.builder()
            .account(account)
            .amount(amount)
            .build();
        return budgetRepository.save(budget);
    }

}
