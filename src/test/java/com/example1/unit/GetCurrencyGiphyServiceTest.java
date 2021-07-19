package com.example1.unit;

import com.example1.dto.properties.DevelopersGiphyProperties;
import com.example1.service.CompareRatesService;
import com.example1.service.GetCurrencyGiphyService;
import com.example1.service.GiphyLookerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GetCurrencyGiphyServiceTest {
    @Autowired
    GetCurrencyGiphyService sut;

    @Autowired
    DevelopersGiphyProperties developersGiphyProperties;

    @MockBean
    CompareRatesService compareRatesService;

    @MockBean
    GiphyLookerService giphyLookerService;

    private static final String CURRENCY = "RUB";
    private static final int COMPARE_RATES_RESULT_POSITIVE = 1;
    private static final int COMPARE_RATES_RESULT_NEGATIVE = -1;
    private static final int COMPARE_RATES_RESULT_ZERO = 0;
    public static final String GIPHY_URL_EXPECTED = "test giphy url";

    @Test
    public void getCurrencyGiphyWhenCompareRatesResultIsPositive() {
        String giphyTagPositive = developersGiphyProperties.getGiphyTagPositive();

        when(compareRatesService.compareRates(CURRENCY)).thenReturn(COMPARE_RATES_RESULT_POSITIVE);
        when(giphyLookerService.searchGiphy(giphyTagPositive)).thenReturn(GIPHY_URL_EXPECTED);

        String giphyUrlResult = sut.getCurrencyGiphy(CURRENCY);

        assertEquals(GIPHY_URL_EXPECTED, giphyUrlResult);
    }

    @Test
    public void getCurrencyGiphyWhenCompareRatesResultIsNegative() {
        String giphyTagNegative = developersGiphyProperties.getGiphyTagNegative();

        when(compareRatesService.compareRates(CURRENCY)).thenReturn(COMPARE_RATES_RESULT_NEGATIVE);
        when(giphyLookerService.searchGiphy(giphyTagNegative)).thenReturn(GIPHY_URL_EXPECTED);

        String giphyUrlResult = sut.getCurrencyGiphy(CURRENCY);

        assertEquals(GIPHY_URL_EXPECTED, giphyUrlResult);
    }

    @Test
    public void getCurrencyGiphyWhenCompareRatesResultIsZero() {
        String giphyTagNeutral = developersGiphyProperties.getGiphyTagNeutral();

        when(compareRatesService.compareRates(CURRENCY)).thenReturn(COMPARE_RATES_RESULT_ZERO);
        when(giphyLookerService.searchGiphy(giphyTagNeutral)).thenReturn(GIPHY_URL_EXPECTED);

        String giphyUrlResult = sut.getCurrencyGiphy(CURRENCY);

        assertEquals(GIPHY_URL_EXPECTED, giphyUrlResult);
    }
}
