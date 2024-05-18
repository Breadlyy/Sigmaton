package ru.sigmaton.moneyhelper.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.model.Account;
import ru.sigmaton.moneyhelper.repository.AccountRepository;

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
}
