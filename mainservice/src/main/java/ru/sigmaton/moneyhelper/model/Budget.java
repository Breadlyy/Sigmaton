package ru.sigmaton.moneyhelper.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    private Long amount;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "budget",
            cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();
}
