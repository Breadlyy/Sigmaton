package ru.sigmaton.moneyhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sigmaton.moneyhelper.model.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
}