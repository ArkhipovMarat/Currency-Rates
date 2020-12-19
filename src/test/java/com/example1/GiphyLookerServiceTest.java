package com.example1;

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
    GiphyLookerService giphyLookerService;

    private static final String TAG = "rich";
    public static final String REGEX = "https://" + "media" + "(\\w*)" + ".giphy.com/media/" + "(\\w*)" + "/giphy.gif";

    @Test
    void searchGiphyTest() {
        String giphyLookerServiceResult = (String) giphyLookerService.searchGiphy(TAG);
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(giphyLookerServiceResult);
        Assertions.assertTrue(matcher.matches());
    }
}
