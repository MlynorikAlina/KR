package com.bsu;

public enum Currency {
    USD("usd", 1),
    EUR("eur", 1.1813),
    BYN("byn", 0.3914),
    RUB("rub", 0.0131),
    DEFAULT("", 1);

    private final double inUsd;
    private final String name;

    Currency(String name, double inUsd) {
        this.inUsd = inUsd;
        this.name = name;
    }

    public static double convert(Currency cur1, Currency cur2, double sum) {
        return cur2.inUsd * sum / cur1.inUsd;
    }

    public static Currency getCurrency(String name) {
        for (Currency cur : values()) {
            if (name.equalsIgnoreCase(cur.name)) return cur;
        }
        return DEFAULT;
    }

}