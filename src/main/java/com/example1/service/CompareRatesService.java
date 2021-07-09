package com.example1.service;

import com.example1.dto.properties.OpenExchangeRatesProperties;
import com.example1.feignclient.CurrencyExchangeServiceClient;
import com.example1.util.DataUtil;
import org.springframework.stereotype.Service;

@Service
public class CompareRatesService {
    private static final String MESSAGE_ERROR_GET_CURRENCY =
            "Can't get currency! Please check property 'app_id' is valid";

    private final OpenExchangeRatesProperties openExchangeRatesProperties;
    private final CurrencyExchangeServiceClient cesClient;
    private final String APP_ID;
    private final String BASE;

    public CompareRatesService(OpenExchangeRatesProperties openExchangeRatesProperties, CurrencyExchangeServiceClient cesClient) {
        this.openExchangeRatesProperties = openExchangeRatesProperties;
        this.cesClient = cesClient;
        this.APP_ID = openExchangeRatesProperties.getApiKey();
        this.BASE = openExchangeRatesProperties.getBase();
    }

    public int compareRates(String currency) {
        double todayCurrencyRate = getCurrencyRate(currency, DataUtil.today());
        double yesterdayCurrencyRate = getCurrencyRate(currency, DataUtil.yesterday());
        return Double.compare(todayCurrencyRate, yesterdayCurrencyRate);
    }

    private double getCurrencyRate(String currency, String date) {
        double currencyRate;

        try {
            currencyRate = cesClient.getHistoricalCurrencyJson(date, APP_ID, BASE).getRates().get(currency);
        } catch (Exception e) {
            throw new RuntimeException(MESSAGE_ERROR_GET_CURRENCY);
        }
        return currencyRate;
    }
}
