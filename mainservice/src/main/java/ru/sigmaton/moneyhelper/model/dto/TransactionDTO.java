package ru.sigmaton.moneyhelper.model.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

    private String name;

    private LocalDateTime timestamp;

    private BigDecimal amount;
    private String categoryName;

}