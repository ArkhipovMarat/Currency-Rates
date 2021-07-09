package com.example1.unit;

import com.example1.dto.currencyrates.CurrencyRatesDto;
import com.example1.feignclient.CurrencyExchangeServiceClient;
import com.example1.service.CompareRatesService;
import com.example1.util.DataUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.HashMap;

@SpringBootTest
public class CompareRatesServiceTest {
    @MockBean(name = "cesClient")
    CurrencyExchangeServiceClient cesClient;

    @Autowired
    CompareRatesService sut;

    private static final String API_KEY = "142d37cbbc8c45d294078efc61e71ddb";
    private static final String CURRENCY = "USD";
    private static final String BASE = "RUB";

    private static final double CURRENCY_VALUE_BIGGER = 65.0;
    private static final double CURRENCY_VALUE_LOWER = 64.0;

    private static final String TODAY_DATE = DataUtil.today();
    private static final String YESTERDAY_DATE = DataUtil.yesterday();


    @Test
    public void compareRatesWhenTodayCurrencyValueBigger() {
        // given
        CurrencyRatesDto currencyRatesDtoToday = getCurrencyRatesDto(CURRENCY_VALUE_BIGGER);
        CurrencyRatesDto currencyRatesDtoYesterday = getCurrencyRatesDto(CURRENCY_VALUE_LOWER);

        Mockito.when(cesClient.getHistoricalCurrencyJson(TODAY_DATE, API_KEY, BASE)).thenReturn(currencyRatesDtoToday);
        Mockito.when(cesClient.getHistoricalCurrencyJson(YESTERDAY_DATE, API_KEY, BASE)).thenReturn(currencyRatesDtoYesterday);

        int compareRatesExpected = 1;

        int compareRatesResult = sut.compareRates(CURRENCY);

        Assertions.assertEquals(compareRatesExpected, compareRatesResult);
    }


    @Test
    public void compareRatesWhenYesterdayCurrencyValueBigger() {
        // given
        CurrencyRatesDto currencyRatesDtoToday = getCurrencyRatesDto(CURRENCY_VALUE_LOWER);
        CurrencyRatesDto currencyRatesDtoYesterday = getCurrencyRatesDto(CURRENCY_VALUE_BIGGER);

        Mockito.when(cesClient.getHistoricalCurrencyJson(TODAY_DATE, API_KEY, BASE)).thenReturn(currencyRatesDtoToday);
        Mockito.when(cesClient.getHistoricalCurrencyJson(YESTERDAY_DATE, API_KEY, BASE)).thenReturn(currencyRatesDtoYesterday);

        int compareRatesExpected = -1;

        int compareRatesResult = sut.compareRates(CURRENCY);

        Assertions.assertEquals(compareRatesExpected, compareRatesResult);
    }

    @Test
    public void compareRatesWhenCurrencyValuesEquals() {
        // given
        CurrencyRatesDto currencyRatesDtoToday = getCurrencyRatesDto(CURRENCY_VALUE_LOWER);
        CurrencyRatesDto currencyRatesDtoYesterday = getCurrencyRatesDto(CURRENCY_VALUE_LOWER);

        Mockito.when(cesClient.getHistoricalCurrencyJson(TODAY_DATE, API_KEY, BASE)).thenReturn(currencyRatesDtoToday);
        Mockito.when(cesClient.getHistoricalCurrencyJson(YESTERDAY_DATE, API_KEY, BASE)).thenReturn(currencyRatesDtoYesterday);

        int compareRatesExpected = 0;

        int compareRatesResult = sut.compareRates(CURRENCY);

        Assertions.assertEquals(compareRatesExpected, compareRatesResult);
    }

    private static CurrencyRatesDto getCurrencyRatesDto(double currencyValue) {
        HashMap<String, Double> ratesMap = new HashMap<>();
        ratesMap.put(CURRENCY, currencyValue);

        return CurrencyRatesDto.builder()
                .rates(ratesMap)
                .build();
    }
}
