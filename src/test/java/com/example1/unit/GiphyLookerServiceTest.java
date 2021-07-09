package com.example1.unit;

import com.example1.dto.giphydata.GiphyDataDto;
import com.example1.feignclient.GiphyServiceClient;
import com.example1.service.GiphyLookerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class GiphyLookerServiceTest {
    @MockBean(name = "gsClient")
    GiphyServiceClient gsClient;

    @Autowired
    GiphyLookerService sut;

    private static final String API_KEY = "7wnTCxzX13dNHjzrZwtktjGAC5okJMbG";
    private static final String TAG = "ANYTHING";
    public static final String GIPHY_URL = "test giphy url";

    @Test
    public void searchGiphyShouldPassSuccess() {
        // given
        GiphyDataDto giphyDataDto = new GiphyDataDto();
        giphyDataDto.setData(giphyDataDto.new Data(GIPHY_URL));

        Mockito.when(gsClient.getGiphyJson(API_KEY,TAG)).thenReturn(giphyDataDto);

        String giphyDataResult = sut.searchGiphy(TAG);

        Assertions.assertEquals(GIPHY_URL,giphyDataResult);
    }
}
