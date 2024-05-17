package ru.sigmaton.moneyhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sigmaton.moneyhelper.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}