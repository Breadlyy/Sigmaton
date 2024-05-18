package ru.sigmaton.moneyhelper.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.repository.BudgetRepository;

@Service
public class BudgetService {

    BudgetRepository budgetRepository;
    CategoryService categoryService;

    @Autowired
    public BudgetService(BudgetRepository budgetRepository, CategoryService categoryService) {
        this.budgetRepository = budgetRepository;
        this.categoryService = categoryService;
    }

    public void saveNewBudget(Budget budget, Category category) {
        Budget savedBudget = budgetRepository.save(budget);
        category.setBudget(savedBudget);
        categoryService.save(category);
    }
}
