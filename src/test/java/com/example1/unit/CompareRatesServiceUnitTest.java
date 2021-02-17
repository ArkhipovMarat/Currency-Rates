package com.example1.unit;

import com.example1.dto.CurrencyRates;
import com.example1.feignclient.CurrencyExchangeServiceClient;
import com.example1.service.CompareRatesService;
import com.example1.util.DataUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;

@SpringBootTest
public class CompareRatesServiceUnitTest {
    @MockBean(name = "cesClient")
    CurrencyExchangeServiceClient cesClient;

    @Autowired
    CompareRatesService compareRatesService;

    private static final String API_KEY = "142d37cbbc8c45d294078efc61e71ddb";
    private static final String CURRENCY = "RUB";
    private static final Double CURRENCY_VALUE_BIGGER = 65.0;
    private static final Double CURRENCY_VALUE_LOWER = 64.0;

    private static final String TODAY_DATE = DataUtil.today();
    private static final String YESTERDAY_DATE = DataUtil.yesterday();

    private final HashMap<String, Double> ratesTodayMap = new HashMap<>();
    private final HashMap<String, Double> ratesYesterdayMap = new HashMap<>();
    private final CurrencyRates currencyRatesToday = new CurrencyRates();
    private final CurrencyRates currencyRatesYesterday = new CurrencyRates();

    @AfterEach
    void clear() {
        ratesTodayMap.clear();
        ratesYesterdayMap.clear();
    }

    @Test
    public void compareRatesWhenTodayCurrencyValueBigger() {
        ratesTodayMap.put(CURRENCY, CURRENCY_VALUE_BIGGER);
        ratesYesterdayMap.put(CURRENCY, CURRENCY_VALUE_LOWER);

        currencyRatesToday.setRates(ratesTodayMap);
        currencyRatesYesterday.setRates(ratesYesterdayMap);

        Mockito.when(cesClient.getHistoricalCurrencyJson(TODAY_DATE, API_KEY)).thenReturn(currencyRatesToday);
        Mockito.when(cesClient.getHistoricalCurrencyJson(YESTERDAY_DATE, API_KEY)).thenReturn(currencyRatesYesterday);

        int compareRatesExpected = 1;
        int compareRatesResult = compareRatesService.compareRates(CURRENCY);

        Assertions.assertEquals(compareRatesExpected, compareRatesResult);
    }

    @Test
    public void compareRatesWhenYesterdayCurrencyValueBigger() {
        ratesTodayMap.put(CURRENCY, CURRENCY_VALUE_LOWER);
        ratesYesterdayMap.put(CURRENCY, CURRENCY_VALUE_BIGGER);

        currencyRatesToday.setRates(ratesTodayMap);
        currencyRatesYesterday.setRates(ratesYesterdayMap);

        Mockito.when(cesClient.getHistoricalCurrencyJson(TODAY_DATE, API_KEY)).thenReturn(currencyRatesToday);
        Mockito.when(cesClient.getHistoricalCurrencyJson(YESTERDAY_DATE, API_KEY)).thenReturn(currencyRatesYesterday);

        int compareRatesExpected = -1;
        int compareRatesResult = compareRatesService.compareRates(CURRENCY);

        Assertions.assertEquals(compareRatesExpected, compareRatesResult);
    }

    @Test
    public void compareRatesWhenCurrencyValuesEquals() {
        ratesTodayMap.put(CURRENCY, CURRENCY_VALUE_LOWER);
        ratesYesterdayMap.put(CURRENCY, CURRENCY_VALUE_LOWER);

        currencyRatesToday.setRates(ratesTodayMap);
        currencyRatesYesterday.setRates(ratesYesterdayMap);

        Mockito.when(cesClient.getHistoricalCurrencyJson(TODAY_DATE, API_KEY)).thenReturn(currencyRatesToday);
        Mockito.when(cesClient.getHistoricalCurrencyJson(YESTERDAY_DATE, API_KEY)).thenReturn(currencyRatesYesterday);

        int compareRatesExpected = 0;
        int compareRatesResult = compareRatesService.compareRates(CURRENCY);

        Assertions.assertEquals(compareRatesExpected, compareRatesResult);
    }
}
