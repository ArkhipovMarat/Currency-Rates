package com.example1.integration;

import com.example1.feignclient.CurrencyExchangeServiceClient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurrencyExchangeServiceClientTest {
    private static final String API_KEY = "142d37cbbc8c45d294078efc61e71ddb";
    private static final String DATE = "2021-07-08";
    private static final String CURRENCY = "USD";
    private static final String BASE = "RUB";
    private static final double EXPECTED_CURRENCY_VALUE = 0.013379;

    @Autowired
    CurrencyExchangeServiceClient sut;

    @Test
    public void getHistoricalCurrencyJson() {
        double resultCurrencyValue = sut.getHistoricalCurrencyJson(DATE, API_KEY, BASE)
                .getRates().get(CURRENCY);
        Assertions.assertEquals(EXPECTED_CURRENCY_VALUE, resultCurrencyValue);
    }
}
