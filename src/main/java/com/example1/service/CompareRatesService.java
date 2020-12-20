package com.example1.service;

import com.example1.feignclient.CurrencyExchangeServiceClient;
import com.example1.util.DataUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CompareRatesService {
    @Value("${openexchangerates.app_id:null}")
    private String appId;

    @Value("${openexchangerates.json_node:rates}")
    private String jsonNod;

    private final CurrencyExchangeServiceClient cesClient;

    private final ObjectMapper objectMapper;

    public CompareRatesService(ObjectMapper objectMapper, CurrencyExchangeServiceClient cesClient) {
        this.objectMapper = objectMapper;
        this.cesClient = cesClient;
    }

    public int compareRates(String currency) {
        Double todayCurrency = getCurrency(currency, DataUtil.today());
        Double yesterdayCurrency = getCurrency(currency, DataUtil.yesterday());
        return Double.compare(todayCurrency, yesterdayCurrency);
    }

    private Double getCurrency(String currency, String date) {
        JsonNode jsonNode;
        double currencyRate;
        try {
            String currencyJson = cesClient.getHistoricalCurrencyJson(date, appId);
            jsonNode = objectMapper.readTree(currencyJson);
            currencyRate = jsonNode.get(jsonNod).get(currency).asDouble();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't get response from exchangerates client! Please check request is valid");
        } catch (NullPointerException e) {
            throw new RuntimeException("Can't get currency! Please check properties " +
                    "'jsonNod', 'app_id', filed 'currency' are valid");
        }
        return currencyRate;
    }
}
