package ru.sigmaton.moneyhelper.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sigmaton.moneyhelper.model.Account;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.services.AccountDetailsService;
import ru.sigmaton.moneyhelper.services.BudgetService;

@RestController
@RequestMapping("/api/v1/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;
    private final AccountDetailsService accountDetailsService;
    @PostMapping("/new")
    public void createNewBudget(@AuthenticationPrincipal UserDetails userDetails,
                                @RequestBody Budget budget,
                                @RequestBody Category category)
    {
        Account account = accountDetailsService.findByLogin(userDetails.getUsername());
        budget.setAccount(account);
        budgetService.saveNewBudget(budget, category);
    }
}
