package ru.sigmaton.moneyhelper.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "category",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

}