package com.example1.service;

import com.example1.dto.OpenExchangeRatesProperties;
import com.example1.feignclient.CurrencyExchangeServiceClient;
import com.example1.util.DataUtil;
import org.springframework.stereotype.Component;

@Component
public class CompareRatesService {
    private static final String MESSAGE_ERROR_GET_CURRENCY =
            "Can't get currency! Please check property 'app_id' is valid";

    private final OpenExchangeRatesProperties openExchangeRatesProperties;
    private final CurrencyExchangeServiceClient cesClient;

    public CompareRatesService(OpenExchangeRatesProperties openExchangeRatesProperties, CurrencyExchangeServiceClient cesClient) {
        this.openExchangeRatesProperties = openExchangeRatesProperties;
        this.cesClient = cesClient;
    }

    public int compareRates(String currency) {
        Double todayCurrencyRate = getCurrencyRate(currency, DataUtil.today());
        Double yesterdayCurrencyRate = getCurrencyRate(currency, DataUtil.yesterday());
        return Double.compare(todayCurrencyRate, yesterdayCurrencyRate);
    }

    private Double getCurrencyRate(String currency, String date) {
        String appId = openExchangeRatesProperties.getApiKey();
        double currencyRate;
        try {
            currencyRate = cesClient.getHistoricalCurrencyJson(date, appId).getRates().get(currency);
        } catch (Exception e) {
            throw new RuntimeException(MESSAGE_ERROR_GET_CURRENCY);
        }
        return currencyRate;
    }
}
