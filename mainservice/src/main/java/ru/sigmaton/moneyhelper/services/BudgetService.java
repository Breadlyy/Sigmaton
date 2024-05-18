package ru.sigmaton.moneyhelper.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.exception.BudgetNotFoundException;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.model.BudgetRequest;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.repository.BudgetRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BudgetService {

    private final AccountDetailsService accountDetailsService;
    private final BudgetRepository budgetRepository;

    public Budget create(Budget budget, String username) {
        return budgetRepository.save(budget);
    }

    public Budget findById(Long budgetId, String username) {
        var budget = budgetRepository.findById(budgetId);
        if (budget.isPresent())
            return budget.get();
        throw new BudgetNotFoundException(budgetId);
    }

    public List<Budget> findAll(String login) {
        return budgetRepository.findAllByAccount_Login(login);
    }

    public void deleteById(Long budgetId) {
        var budget = findById(budgetId);
        budgetRepository.delete(budget);
    }

    public Budget update(Budget budget) {
        var id = budget.getId();
        if (budgetRepository.existsById(id))
            return budgetRepository.save(budget);
        throw new BudgetNotFoundException(id);
    }

}
