package com.example1.unit;

import com.example1.dto.GiphyData;
import com.example1.feignclient.GiphyServiceClient;
import com.example1.service.GiphyLookerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class GiphyLookerServiceUnitTest {
    @MockBean(name = "gsClient")
    GiphyServiceClient gsClient;

    @Autowired
    GiphyLookerService giphyLookerService;

    private static final String API_KEY = "7wnTCxzX13dNHjzrZwtktjGAC5okJMbG";
    private static final String TAG = "ANYTHING";
    public static final String GIPHY_URL = "test giphy url";

    @Test
    public void searchGiphySuccess() {
        GiphyData giphyData = new GiphyData();
        giphyData.setData(giphyData.new Data(GIPHY_URL));

        Mockito.when(gsClient.getGiphyJson(API_KEY,TAG)).thenReturn(giphyData);

        String giphyDataExpected = GIPHY_URL;
        String giphyDataResult = giphyLookerService.searchGiphy(TAG);

        Assertions.assertEquals(giphyDataExpected,giphyDataResult);
    }
}
