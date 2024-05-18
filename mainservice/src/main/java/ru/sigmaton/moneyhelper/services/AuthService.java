package ru.sigmaton.moneyhelper.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.jwtService.JwtService;
import ru.sigmaton.moneyhelper.model.Account;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.repository.AccountRepository;
import ru.sigmaton.moneyhelper.securityUtils.AuthenticationRequest;
import ru.sigmaton.moneyhelper.securityUtils.AuthenticationResponse;
import ru.sigmaton.moneyhelper.utils.RegisterRequest;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    public AuthenticationResponse register(RegisterRequest request) {
        var account = Account.builder()
                .login(request.getLogin())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        Budget budget = Budget.builder()
                .amount(0L)
                .account(account)
                .categories(Collections.emptyList()).build();

        account.setBudget(budget);

        accountRepository.save(account);
        var jwtToken = jwtService.generateToken(account);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        var account = accountRepository.findByLogin(request.getLogin())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(account);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

}
