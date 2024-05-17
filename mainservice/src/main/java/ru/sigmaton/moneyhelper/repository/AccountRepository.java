package ru.sigmaton.moneyhelper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.sigmaton.moneyhelper.model.Account;

public interface AccountRepository extends JpaRepository<Account, String> {
}