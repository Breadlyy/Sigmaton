package ru.sigmaton.moneyhelper.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sigmaton.moneyhelper.model.Transaction;
import ru.sigmaton.moneyhelper.services.TransactionService;

@RestController
@RequestMapping("/api/v1/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/new")
    public void createNewTransaction(@RequestBody Transaction transaction)
    {
        transactionService.saveNewTransaction(transaction);
    }
}
