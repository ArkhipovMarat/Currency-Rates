package com.example1.unit;

import com.example1.dto.GiphyData;
import com.example1.feignclient.GiphyServiceClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class GiphyServiceClientUnitTest {
    @Autowired
    GiphyServiceClient gsClient;

    private static final String API_KEY = "7wnTCxzX13dNHjzrZwtktjGAC5okJMbG";
    private static final String TAG = "ANYTHING";
    public static final String REGEX = "https://" + "media" + "(\\w*)" + ".giphy.com/media/" + "(\\w*)" + "/giphy.gif";

    @Test
    void getGiphyJson() {
        GiphyData giphyData = gsClient.getGiphyJson(API_KEY, TAG);
        String giphyUrl = giphyData.getData().getImage_original_url();

        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(giphyUrl);

        Assertions.assertTrue(matcher.matches());
    }
}
