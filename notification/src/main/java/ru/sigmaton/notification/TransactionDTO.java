package ru.sigmaton.notification;

import lombok.*;

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

}