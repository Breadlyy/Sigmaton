package ru.sigmaton.moneyhelper.dto;

import ru.sigmaton.moneyhelper.model.enums.Type;

public record CategoryDto(
        String name,
        Type type,

        Long plan,

        Long real,

        Long budgetId
){}
