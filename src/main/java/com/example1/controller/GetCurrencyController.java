package com.example1.controller;

import com.example1.service.GetCurrencyGiphyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetCurrencyController {
    private final String API_PATH = "/rates/{currency}";
    private final String PATH_VAR_CURRENCY = "currency";

    private final String VIEW_NAME_INDEX = "index";
    private final String MODEL_NAME_GIF = "gif";

    private final String VIEW_NAME_ERROR = "trouble";
    private final String MODEL_NAME_ERROR = "errorMsg";

    private final GetCurrencyGiphyService getCurrencyGiphyService;

    public GetCurrencyController(GetCurrencyGiphyService getCurrencyGiphyService) {
        this.getCurrencyGiphyService = getCurrencyGiphyService;
    }

    @GetMapping(API_PATH)
    public ModelAndView getCurrencyGiphy(@PathVariable(PATH_VAR_CURRENCY) String currency) {
        String giphyUrl = getCurrencyGiphyService.getCurrencyGiphy(currency);
        return new ModelAndView(VIEW_NAME_INDEX, MODEL_NAME_GIF, giphyUrl);
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRTE(RuntimeException e) {
        return new ModelAndView(VIEW_NAME_ERROR, MODEL_NAME_ERROR, e.getMessage());
    }
}
