package com.example1.integration;

import com.example1.service.GiphyLookerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class GiphyLookerServiceTest {
    @Autowired
    GiphyLookerService sut;

    private static final String TAG = "ANYTHING";
    public static final String REGEX = "https://" + "media" + "(\\w*)" + ".giphy.com/media/" + "(\\w*)" + "/giphy.gif";

    @Test
    void searchGiphyMethodTest() {
        String giphyLookerServiceResult = sut.searchGiphy(TAG);
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(giphyLookerServiceResult);
        Assertions.assertTrue(matcher.matches());
    }
}
