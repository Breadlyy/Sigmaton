package ru.sigmaton.moneyhelper.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private LocalDateTime timestamp;

    private Long amount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}