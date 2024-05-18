package ru.sigmaton.moneyhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sigmaton.moneyhelper.model.Budget;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findAllByAccount_Id(Long id);

}