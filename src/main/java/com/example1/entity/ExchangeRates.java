package com.example1.entity;

import java.util.Map;

public class ExchangeRates {
    private Map<String,Double> rates;

    public ExchangeRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public ExchangeRates() {
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}
