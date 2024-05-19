package ru.sigmaton.moneyhelper.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.model.Account;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.model.Category;
import ru.sigmaton.moneyhelper.model.Transaction;
import ru.sigmaton.moneyhelper.model.enums.Type;
import ru.sigmaton.moneyhelper.repository.AccountRepository;
import ru.sigmaton.moneyhelper.repository.BudgetRepository;
import ru.sigmaton.moneyhelper.repository.CategoryRepository;
import ru.sigmaton.moneyhelper.repository.TransactionRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {


    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final BudgetRepository budgetRepository;
    private final TransactionRepository transactionRepository;
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return accountRepository.findByLogin(login).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User: " + login + " not found"
                ));
    }

    public Account findByLogin(String username) {
        return accountRepository.findByLogin(username).orElseThrow(
                () -> new UsernameNotFoundException(
                        "User: " + username + " not found"
                ));
    }

    public Account findById(Long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public void createNewTransaction(List<String> transaction) {
        Account account = findById(Long.valueOf(transaction.get(4)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
        LocalDateTime dateTime = LocalDateTime.parse(transaction.get(1), formatter);
        Transaction newTransaction = new Transaction(transaction.get(0), dateTime, Long.valueOf(transaction.get(2)));

        Budget budget = account.getBudget();
        List<Category> categories = budget.getCategories();
        int flag = 0;
        for (int i = 0; i < categories.size(); i++) {
            if(categories.get(i).getName().equals(newTransaction.getName()))
            {
                categories.get(i).addTransaction(newTransaction);
                flag++;
                break;
            }
        }
        if (flag > 0)
        {
            Type type;
            if(newTransaction.getAmount() > 0)
            {
               type = Type.INCOME;
            }
            else{
                type = Type.SPENDING;
            }
            List<Transaction> transactions = new ArrayList<>();
            transactions.add(newTransaction);
            Category category = Category.builder()
                    .transactions(transactions).budget(budget)
                    .type(type).build();
            categoryRepository.save(category);
        }
        budget.setCategories(categories);
        budgetRepository.save(budget);
        account.setBudget(budget);
        accountRepository.save(account);
        transactionRepository.save(newTransaction);
    }
}
