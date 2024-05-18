package ru.sigmaton.moneyhelper.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sigmaton.moneyhelper.exception.CategoryNotFoundException;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.repository.CategoryRepository;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Test
    void getCategories() {
        String username = "username";
        Principal principal = () -> username;

        // Создаем мок для BudgetService
        BudgetService budgetServiceMock = mock(BudgetService.class);
        when(budgetServiceMock.getBudget(principal)).thenReturn(new Budget());

        // Внедряем мок в тестируемый сервис
        CategoryService underTest = new CategoryService(budgetServiceMock, categoryRepository);

        List<Category> categories = underTest.getCategories(principal);

        // Проверяем, что метод getBudget был вызван с правильным аргументом
        verify(budgetServiceMock).getBudget(principal);

        // Проверяем, что метод вернул пустой список, если в бюджете нет категорий
        assertThat(categories).isEmpty();
    }


    @Test
    void getCategoryWillThrowWhenCategoryNotFound() {
        Long categoryId = 1L;
        String username = "username";
        Principal principal = () -> username;

        // Создаем мок для BudgetService
        BudgetService budgetServiceMock = mock(BudgetService.class);
        when(budgetServiceMock.getBudget(principal)).thenReturn(new Budget());

        // Создаем мок для CategoryRepository
        CategoryRepository categoryRepositoryMock = mock(CategoryRepository.class);


        // Внедряем моки в тестируемый сервис
        CategoryService underTest = new CategoryService(budgetServiceMock, categoryRepositoryMock);

        assertThatThrownBy(() -> underTest.getCategory(categoryId, principal))
                .isInstanceOf(CategoryNotFoundException.class);

        verify(budgetServiceMock).getBudget(principal);

    }



}