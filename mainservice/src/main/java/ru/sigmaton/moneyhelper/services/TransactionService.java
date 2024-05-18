package ru.sigmaton.moneyhelper.services;

import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.exception.TransactionNotFoundException;
import ru.sigmaton.moneyhelper.model.Transaction;
import ru.sigmaton.moneyhelper.repository.TransactionRepository;

import java.security.Principal;
import java.util.List;

@Service
public class TransactionService {

    private final BudgetService budgetService;
    private final CategoryService categoryService;
    private final TransactionRepository transactionRepository;

    public TransactionService(BudgetService budgetService, CategoryService categoryService, TransactionRepository transactionRepository) {
        this.budgetService = budgetService;
        this.categoryService = categoryService;
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> getTransactions(Long categoryId, Principal principal) {
        var category = categoryService.getCategory(categoryId, principal);
        return category.getTransactions();
    }

    public Transaction getTransaction(Long categoryId, Long transactionId, Principal principal) {
        var transaction = getTransactions(categoryId, principal)
                .stream()
                .filter(t -> t.getId().equals(transactionId))
                .findFirst();
        if (transaction.isPresent())
            return transaction.get();
        throw new TransactionNotFoundException(transactionId);
    }

    public void deleteTransaction(Long categoryId, Transaction transaction, Principal principal) {
        getTransaction(categoryId, transaction.getId(), principal);
        budgetService.rollbackAmount(transaction, principal);
        transactionRepository.delete(transaction);
    }

    public Transaction createTransaction(Long categoryId, Transaction transaction, Principal principal) {
        transaction.setCategory(categoryService.getCategory(categoryId, principal));
        budgetService.updateAmount(transaction, principal);
        return transactionRepository.save(transaction);
    }

}
