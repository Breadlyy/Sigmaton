package ru.sigmaton.moneyhelper.services;

import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.exception.BudgetNotExists;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.model.Transaction;
import ru.sigmaton.moneyhelper.repository.BudgetRepository;

import java.security.Principal;

@Service
public class BudgetService {

    private final AccountDetailsService accountService;



    public BudgetService(AccountDetailsService accountService) {
        this.accountService = accountService;

    }

    public Budget getBudget(Principal principal) {
        var account = accountService.findByLogin(principal.getName());
        var budget = account.getBudget();
        if (budget == null)
            throw new BudgetNotExists();
        return budget;
    }



    public void updateAmount(Transaction transaction, Principal principal) {
        var budget = getBudget(principal);
        switch (transaction.getCategory().getType()) {
            case INCOME -> budget.setAmount(budget.getAmount() + transaction.getAmount());
            case SPENDING -> budget.setAmount(budget.getAmount() - transaction.getAmount());
        }
        budgetRepository.save(budget);
    }

    public void rollbackAmount(Transaction transaction, Principal principal) {
        var budget = getBudget(principal);
        switch (transaction.getCategory().getType()) {
            case INCOME -> budget.setAmount(budget.getAmount() - transaction.getAmount());
            case SPENDING -> budget.setAmount(budget.getAmount() + transaction.getAmount());
        }
        budgetRepository.save(budget);
    }

}
