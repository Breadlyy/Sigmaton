package ru.sigmaton.moneyhelper.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "budget")
public class Budget {

    @Id
    @GeneratedValue
    private Long id;

    private Long amount;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "budget",
            cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

}
