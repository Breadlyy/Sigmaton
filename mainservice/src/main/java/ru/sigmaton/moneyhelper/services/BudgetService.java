package ru.sigmaton.moneyhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.model.BudgetRequest;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.repository.BudgetRepository;

import java.util.List;

@Service
public class BudgetService {

    private BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository, CategoryService categoryService) {
        this.budgetRepository = budgetRepository;
    }

    public Budget create(Budget budget) {
        return budgetRepository.save(budget);
    }

    public Budget findById(Long budgetId) {
        return budgetRepository.findById(budgetId).get();
    }

    public List<Budget> findAll(Long userId) {
        return budgetRepository.findAllByAccount_Id(userId);
    }

}
