package ru.sigmaton.moneyhelper.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.sigmaton.moneyhelper.model.enums.Type;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    private Long plan;

    private Long real;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "category",
            orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public Category(String name, Type type, Long plan) {
        this.name = name;
        this.type = type;
        this.plan = plan;
    }
}