package ru.sigmaton.moneyhelper.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.services.BudgetService;
import ru.sigmaton.moneyhelper.services.CategoryService;

@RestController
@RequestMapping("/api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final BudgetService budgetService;
    @PostMapping("/new")
    public ResponseEntity<Category> createNewBudget(@RequestParam("budgetId") Long budgetId)
    {
        Category category = new Category();
        category.setBudget(budgetService.findById(budgetId));
        return ResponseEntity.ok(categoryService.save(category));
    }
}
