package ru.sigmaton.moneyhelper.model;

public enum Currency {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR");
    private String abbr;

    Currency(String abbr) {
        this.abbr = abbr;
    }
}
