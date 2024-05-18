package ru.sigmaton.moneyhelper.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.sigmaton.moneyhelper.jwtService.JwtService;
import ru.sigmaton.moneyhelper.model.Account;
import ru.sigmaton.moneyhelper.model.Budget;
import ru.sigmaton.moneyhelper.repository.AccountRepository;
import ru.sigmaton.moneyhelper.securityUtils.AuthenticationRequest;
import ru.sigmaton.moneyhelper.utils.RegisterRequest;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    private AuthService underTest;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private AccountRepository accountRepository;



    @BeforeEach
    void setUp() {
       underTest = new AuthService(
               passwordEncoder,
               jwtService,
               authenticationManager,
               accountRepository
       );
    }

    @Test
    void register() {

        String login = "login";
        String password = "password";


        RegisterRequest request = new RegisterRequest(
                login,
                password
        );

        String passwordHash = "pq309ern1";



        when(passwordEncoder.encode(request.getPassword())).thenReturn(passwordHash);

        underTest.register(request);

        ArgumentCaptor<Account> customerArgumentCaptor = ArgumentCaptor.forClass(
                Account.class
        );

        verify(accountRepository).save(customerArgumentCaptor.capture());

        Account capturedAccount = customerArgumentCaptor.getValue();

        assertThat(capturedAccount.getLogin()).isEqualTo(request.getLogin());
        assertThat(capturedAccount.getPassword()).isEqualTo(passwordHash);
        assertThat(capturedAccount.getBudget()).isNotEqualTo(null);

    }

    @Test
    void authenticate() {
        AuthenticationRequest request = new AuthenticationRequest(
                "user",
                "password"
        );

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        );

        Account account = new Account(
                1L,
                "user",
                "password",
                new Budget()
        );

        Authentication mockAuthentication = mock(Authentication.class);


        when(authenticationManager.authenticate(token)).thenReturn(mockAuthentication);
        when(accountRepository.findByLogin(request.getLogin())).thenReturn(Optional.of(account));

        underTest.authenticate(request);

        verify(authenticationManager).authenticate(
                token
        );

        verify(jwtService).generateToken(account);


    }

    @Test
    void authenticateWillThrow() {


        AuthenticationRequest request = new AuthenticationRequest(
                "user",
                "password"
        );

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                request.getLogin(),
                request.getPassword()
        );


        Account account = new Account(
                1L,
                "user",
                "password",
                new Budget()
        );

        Authentication mockAuthentication = mock(Authentication.class);



        when(authenticationManager.authenticate(token)).thenReturn(mockAuthentication);
        when(accountRepository.findByLogin(request.getLogin())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> underTest.authenticate(request))
                .isInstanceOf(RuntimeException.class);

        verify(jwtService, never()).generateToken(account);
    }
}