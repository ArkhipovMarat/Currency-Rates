package com.example1.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="currency-exchange", url = "${openexchangerates.api_url}")
public interface CurrencyExchangeServiceClient {
    String API_PATH = "/{date}.json";

    @GetMapping(API_PATH)
    String getHistoricalCurrencyJson(@PathVariable("date") String date, @RequestParam("app_id") String appId);
}
