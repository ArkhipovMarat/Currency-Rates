package com.example1.service;

import com.example1.feignclient.CurrencyExchangeServiceClient;
import com.example1.util.DataService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompareRatesService {
    @Value("${openexchangerates.app_id:null}")
    private String appId;

    @Autowired
    private CurrencyExchangeServiceClient cesClient;

    private final ObjectMapper objectMapper;

    public CompareRatesService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public int compareRates(String currency) {
        Double todayCurrency = getCurrency(currency, DataService.today());
        Double yesterdayCurrency = getCurrency(currency, DataService.yesterday());
        return Double.compare(todayCurrency, yesterdayCurrency);
    }

    private Double getCurrency(String currency, String date)  {
        JsonNode jsonNode = null;
        try {
            String json = cesClient.getHistoricalCurrencyJson(date, appId);
            jsonNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonNode.get("rates").get(currency).asDouble();
    }
}
