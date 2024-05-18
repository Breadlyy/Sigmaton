package ru.sigmaton.moneyhelper.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.exception.CategoryNotFoundException;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.repository.CategoryRepository;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final BudgetService budgetService;
    private final CategoryRepository categoryRepository;

    public List<Category> getCategories(Principal principal) {
        return budgetService.getBudget(principal).getCategories();
    }

    public Category getCategory(Long id, Principal principal) {
        var category = getCategories(principal).stream().filter(c -> c.getId().equals(id)).findFirst();
        if (category.isEmpty()){
            throw new CategoryNotFoundException(id);
        }

        return category.get();

    }

    public Category createCategory(Category category, Principal principal) {
        var budget = budgetService.getBudget(principal);
        category.setBudget(budget);
        return categoryRepository.save(category);
    }

    public Category updateCategory(Category category, Principal principal) {
        getCategory(category.getId(), principal);
        return categoryRepository.save(category);
    }

    public void deleteCategory(Category category, Principal principal) {
        getCategory(category.getId(), principal);
        categoryRepository.delete(category);
    }

}
