package com.example1.feignclient;

import com.example1.entity.ExchangeRates;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="currency-exchange", url = "${openexchangerates.api_url}")
public interface CurrencyExchangeServiceProxy {

    @GetMapping("/{date}.json?app_id={appId}")
    public ExchangeRates getHistoricalCurrency(@PathVariable("date") String date, @PathVariable("appId") String appId);
}
