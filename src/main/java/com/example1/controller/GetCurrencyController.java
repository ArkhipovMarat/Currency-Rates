package com.example1.controller;

import com.example1.service.CompareRatesService;
import com.example1.service.GiphyLookerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetCurrencyController {
    private final String VIEW_NAME = "index";
    private final String ERROR_VIEW = "trouble";
    private final String API_PATH = "/rates/{currency}";
    private final String PATH_VAR_CURRENCY = "currency";
    private final String MODEL_NAME_GIF = "gif";
    private final String MODEL_NAME_ERROR = "errorMsg";

    private final CompareRatesService compareRatesService;
    private final GiphyLookerService giphyLookerService;

    @Value("${giphy.tag.positive}")
    private String TAG_POSITIVE;

    @Value("${giphy.tag.negative}")
    private String TAG_NEGATIVE;

    @Value("${giphy.tag.neutral}")
    private String TAG_NEUTRAL;

    public GetCurrencyController(CompareRatesService compareRatesService, GiphyLookerService giphyLookerService) {
        this.compareRatesService = compareRatesService;
        this.giphyLookerService = giphyLookerService;
    }


    @GetMapping(API_PATH)
    public ModelAndView getCurrencyGiphy(@PathVariable(PATH_VAR_CURRENCY) String currency) {
        int compareRatesResult = compareRates(currency);
        String giphyUrl = getGiphy(compareRatesResult);
        return new ModelAndView(VIEW_NAME, MODEL_NAME_GIF, giphyUrl);
    }

    @GetMapping("")
    public ModelAndView handleBadRequest() {
        String message = "Bad request! Please type correct API request";
        return new ModelAndView(ERROR_VIEW, MODEL_NAME_ERROR, message);
    }

    private int compareRates(String currency) {
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

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRTE(RuntimeException e) {
        return new ModelAndView(ERROR_VIEW, MODEL_NAME_ERROR, e.getMessage());
    }
}
