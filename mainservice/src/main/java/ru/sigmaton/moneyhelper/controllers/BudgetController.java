package ru.sigmaton.moneyhelper.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.services.AccountDetailsService;
import ru.sigmaton.moneyhelper.services.BudgetService;

@RestController
@RequestMapping("/api/v1/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;
    private final AccountDetailsService accountDetailsService;
    @PostMapping("/new")
    public ResponseEntity<Budget> createNewBudget()
    {
        return ResponseEntity.ok(budgetService.saveNewBudget(new Budget()));
    }
    @GetMapping("/getBudgetById")
    public ResponseEntity<Budget> getBudgetById(@RequestParam("budgetId") Long budgetId)
    {
        return ResponseEntity.ok(budgetService.findById(budgetId));
    }
    @GetMapping("/test")
    public void test(@RequestParam("text")String text)
    {
        System.out.println(text);
    }
    @GetMapping("/budgets")
    public String getBudgets(Model model)
    {
        return "budgets";
    }
}
