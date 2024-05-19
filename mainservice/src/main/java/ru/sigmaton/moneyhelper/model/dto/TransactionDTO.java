package ru.sigmaton.moneyhelper.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private String name;

    private LocalDateTime timestamp;

    private BigDecimal amount;

    private String categoryName;

    private String accountId;

}