package ru.sigmaton.moneyhelper.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sigmaton.moneyhelper.model.Transaction;
import ru.sigmaton.moneyhelper.services.TransactionService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/budget/categories/{categoryId}/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getTransactions (
            @PathVariable Long categoryId,
            Principal principal)
    {
        return transactionService.getTransactions(categoryId, principal);
    }

    @GetMapping("/{transactionId}")
    public Transaction getTransaction (
            @PathVariable Long categoryId,
            @PathVariable Long transactionId,
            Principal principal)
    {
        return transactionService.getTransaction(categoryId, transactionId, principal);
    }

    @DeleteMapping
    public void deleteTransaction (
            @PathVariable Long categoryId,
            @RequestBody Transaction transaction,
            Principal principal)
    {
        transactionService.deleteTransaction(categoryId, transaction, principal);
    }

    @PostMapping
    public Transaction createTransaction (
            @PathVariable Long categoryId,
            @RequestBody Transaction transaction,
            Principal principal)
    {
        return transactionService.createTransaction(categoryId, transaction, principal);
    }

}
