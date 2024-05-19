package ru.sigmaton.moneyhelper.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.model.Account;
import ru.sigmaton.moneyhelper.model.Transaction;
import ru.sigmaton.moneyhelper.repository.AccountRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountDetailsService implements UserDetailsService {


    private final AccountRepository accountRepository;

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
    }
}
