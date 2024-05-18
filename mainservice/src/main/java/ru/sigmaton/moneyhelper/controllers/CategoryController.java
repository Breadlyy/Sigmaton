package ru.sigmaton.moneyhelper.controllers;

import org.springframework.web.bind.annotation.*;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.services.BudgetService;
import ru.sigmaton.moneyhelper.services.CategoryService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/budget/categories")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService, BudgetService budgetService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<Category> getCategories(Principal principal)
    {
        return categoryService.getCategories(principal);
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id, Principal principal)
    {
        return categoryService.getCategory(id, principal);
    }

    @PostMapping
    public Category createCategory(@RequestBody Category category, Principal principal)
    {
        return categoryService.createCategory(category, principal);
    }

    @PutMapping
    public Category updateCategory(@RequestBody Category category, Principal principal)
    {
        return categoryService.updateCategory(category, principal);
    }

    @DeleteMapping
    public Category deleteCategory(@RequestBody Category category, Principal principal)
    {
        return categoryService.deleteCategory(category, principal);
    }

}
