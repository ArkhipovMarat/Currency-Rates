package com.example1.controller;

import com.example1.service.CompareRatesService;
import com.example1.service.GiphyLookerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ApplicationController {
    private CompareRatesService compareRatesService;

    private GiphyLookerService giphyLookerService;

    @Value("${giphy.tag.positive}")
    private String TAG_POSITIVE;

    @Value("${giphy.tag.negative}")
    private String TAG_NEGATIVE;

    @Value("${giphy.tag.neutral}")
    private String TAG_NEUTRAL;

    public ApplicationController(CompareRatesService compareRatesService, GiphyLookerService giphyLookerService) {
        this.compareRatesService = compareRatesService;
        this.giphyLookerService = giphyLookerService;
    }

    @GetMapping("/rates/{currency}")
    public ModelAndView getCurrencyGiphy(@PathVariable("currency") String currency) {
        int compareRatesResult = compareRates(currency);

        String giphyUrl = getGiphy(compareRatesResult);

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("gif", giphyUrl);

        return modelAndView;
    }

    private int compareRates(String currency)  {
        return compareRatesService.compareRates(currency);
    }

    private String getGiphy(int compareRatesResult) {
        String giphyUrl = "";
        if (compareRatesResult == 1) {
            giphyUrl = giphyLookerService.searchGiphy(TAG_POSITIVE);
        } else if (compareRatesResult == -1) {
            giphyUrl = giphyLookerService.searchGiphy(TAG_NEGATIVE);
        } else if (compareRatesResult == 0) {
            giphyUrl = giphyLookerService.searchGiphy(TAG_NEUTRAL);
        }
        return giphyUrl;
    }
}
