package ru.sigmaton.moneyhelper.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.sigmaton.moneyhelper.model.Account;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.model.BudgetRequest;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.services.AccountDetailsService;
import ru.sigmaton.moneyhelper.services.BudgetService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/budgets")
@Slf4j
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @PostMapping
    public Budget newBudget(@RequestBody Budget budget, Principal principal) {
        var who = principal.getName();
        return budgetService.create(budget, who);
    }

    @GetMapping
    public List<Budget> getAll(Principal principal)
    {
        var who = principal.getName();
        return budgetService.findAll(who);
    }

    @GetMapping("/{id}")
    public Budget getById(@PathVariable Long id, Principal principal)
    {
        var who = principal.getName();
        return budgetService.findById(id, who);
    }

}
