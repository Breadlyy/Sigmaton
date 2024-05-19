package ru.sigmaton.moneyhelper.model;

import jakarta.persistence.*;
import lombok.*;
import ru.sigmaton.moneyhelper.model.enums.Type;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Enumerated(EnumType.ORDINAL)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "category",
            orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction)
    {
        transactions.add(transaction);
    }

}