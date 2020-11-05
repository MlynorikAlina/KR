package com.bsu;

public class Currency {
    static double toUSD(String cur, double val) {
        switch (cur.toLowerCase()) {
            case "eur":
                return val / 0.8503;
            case "byn":
                return val / 2.6152;
            case "rub":
                return val / 78.2104;
            case "uah":
                return val / 28.3826;
            default:
                return val;
        }
    }
    static double fromUSD(String cur, double val) {
        switch (cur.toLowerCase()) {
            case "eur":
                return val * 0.8503;
            case "byn":
                return val * 2.6152;
            case "rub":
                return val * 78.2104;
            case "uah":
                return val * 28.3826;
            default:
                return val;
        }
    }
}