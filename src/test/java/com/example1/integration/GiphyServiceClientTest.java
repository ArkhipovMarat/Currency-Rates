package com.example1.integration;

import com.example1.dto.giphydata.GiphyDataDto;
import com.example1.feignclient.GiphyServiceClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class GiphyServiceClientTest {
    @Autowired
    GiphyServiceClient sut;

    private static final String API_KEY = "7wnTCxzX13dNHjzrZwtktjGAC5okJMbG";
    private static final String TAG = "ANYTHING";
    public static final String REGEX = "https://" + "media" + "(\\w*)" + ".giphy.com/media/" + "(\\w*)" + "/giphy.gif";

    @Test
    void getGiphyJson() {
        GiphyDataDto giphyDataDto = sut.getGiphyJson(API_KEY, TAG);
        String giphyUrl = giphyDataDto.getData().getImage_original_url();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(giphyUrl);

        assertTrue(matcher.matches());
    }
}
