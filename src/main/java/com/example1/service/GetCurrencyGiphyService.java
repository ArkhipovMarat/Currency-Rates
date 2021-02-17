package com.example1.service;

import com.example1.dto.DevelopersGiphyProperties;
import org.springframework.stereotype.Service;

@Service
public class GetCurrencyGiphyService {
    private final CompareRatesService compareRatesService;
    private final GiphyLookerService giphyLookerService;
    private final DevelopersGiphyProperties developersGiphyProperties;

    public GetCurrencyGiphyService(CompareRatesService compareRatesService, GiphyLookerService giphyLookerService, DevelopersGiphyProperties developersGiphyProperties) {
        this.compareRatesService = compareRatesService;
        this.giphyLookerService = giphyLookerService;
        this.developersGiphyProperties = developersGiphyProperties;
    }

    public String getCurrencyGiphy(String currency) {
        String giphyTag = "";

        switch (compareRatesService.compareRates(currency)) {
            case 1:
                giphyTag = developersGiphyProperties.getGiphyTagPositive();
                break;
            case -1:
                giphyTag = developersGiphyProperties.getGiphyTagNegative();
                break;
            case 0:
                giphyTag = developersGiphyProperties.getGiphyTagNeutral();
                break;
        }

        return giphyLookerService.searchGiphy(giphyTag);
    }
}
