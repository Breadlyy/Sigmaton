package ru.sigmaton.moneyhelper.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime timestamp;

    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Transaction(String name, LocalDateTime timestamp, BigDecimal amount) {
        this.name = name;
        this.timestamp = timestamp;
        this.amount = amount;
    }
}