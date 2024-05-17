package ru.sigmaton.moneyhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sigmaton.moneyhelper.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}