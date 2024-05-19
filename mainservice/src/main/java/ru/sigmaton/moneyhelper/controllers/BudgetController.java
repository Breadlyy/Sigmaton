package ru.sigmaton.moneyhelper.controllers;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.web.bind.annotation.*;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.services.BudgetService;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping("/api/v1/budget")
public class BudgetController {

    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @GetMapping
    public Budget getBudget(Principal principal) {
        return budgetService.getBudget(principal);
    }

}
