package ru.sigmaton.moneyhelper.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue
    private String id;

    private String login;

    private String password;

    private String email;

    //    private Currency currency;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "account",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Budget> budgets;

}
