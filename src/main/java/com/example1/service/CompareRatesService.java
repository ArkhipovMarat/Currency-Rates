package com.example1.service;

import com.example1.feignclient.CurrencyExchangeServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompareRatesService {
    @Value("${openexchangerates.app_id}")
    private String appId="";

    @Autowired
    private CurrencyExchangeServiceProxy cesProxy;

    public int compareRates(String currency) {
        Double todayCurrency = cesProxy.getHistoricalCurrency(DataService.today(), appId)
                .getRates().get(currency);
        Double yesterdayCurrency = cesProxy.getHistoricalCurrency(DataService.yesterday(), appId)
                .getRates().get(currency);
        return Double.compare(todayCurrency, yesterdayCurrency);
    }
}
