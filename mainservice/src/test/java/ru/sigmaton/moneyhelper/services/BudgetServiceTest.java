package ru.sigmaton.moneyhelper.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.sigmaton.moneyhelper.exception.BudgetNotExists;
import ru.sigmaton.moneyhelper.model.Account;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.repository.AccountRepository;

import java.security.Principal;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BudgetServiceTest {

    private BudgetService underTest;
    @Mock
    private AccountRepository accountRepository;

    private AccountDetailsService accountDetailsService;

    @BeforeEach
    void setUp() {
        accountDetailsService = new AccountDetailsService(accountRepository);
        underTest = new BudgetService(accountDetailsService);
    }

    @Test
    void getBudget() {

        String username = "username";

        Account account = Account.builder()
                .id(1L).login(username).password("password").build();

        Budget expected = Budget.builder()
                .id(1L)
                .amount(0L)
                .account(account)
                .categories(Collections.emptyList())
                .build();

        account.setBudget(expected);


        when(accountRepository.findByLogin(username)).thenReturn(Optional.of(account));
        Principal principal = () -> username;

        Budget actual = underTest.getBudget(principal);

        verify(accountRepository).findByLogin(username);

        assertThat(actual).isEqualTo(expected);



    }




}