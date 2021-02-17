package com.example1.unit;

import com.example1.feignclient.CurrencyExchangeServiceClient;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CurrencyExchangeServiceClientUnitTest {
    private static final String API_KEY = "142d37cbbc8c45d294078efc61e71ddb";
    private static final String DATE = "2020-02-14";
    private static final String CURRENCY = "RUB";
    private static final Double RESULT_CURRENCY_VALUE = 63.5304;

    @Autowired
    CurrencyExchangeServiceClient currencyExchangeServiceClient;

    @Test
    public void getHistoricalCurrencyJson() {
        Double TEST_CURRENCY_VALUE = currencyExchangeServiceClient.getHistoricalCurrencyJson(DATE, API_KEY).getRates().get(CURRENCY);
        Assertions.assertEquals(RESULT_CURRENCY_VALUE, TEST_CURRENCY_VALUE);
    }
}
