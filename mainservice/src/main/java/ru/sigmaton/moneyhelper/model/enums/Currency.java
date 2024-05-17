package ru.sigmaton.moneyhelper.model.enums;

public enum Currency {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR");
    private String abbr;

    Currency(String abbr) {
        this.abbr = abbr;
    }
}
