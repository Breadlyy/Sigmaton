package ru.sigmaton.moneyhelper.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.sigmaton.moneyhelper.model.Account;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByLogin(String login);

}