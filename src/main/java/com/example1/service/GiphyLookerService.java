package com.example1.service;

import com.example1.dto.properties.DevelopersGiphyProperties;
import com.example1.feignclient.GiphyServiceClient;
import org.springframework.stereotype.Service;

@Service
public class GiphyLookerService {
    private static final String MESSAGE_ERROR_GET_GIPHY =
            "Can't get giphy! Please check properties 'app_id', 'giphy_tag' are valid! See also ";

    private final DevelopersGiphyProperties developersGiphyProperties;
    private final GiphyServiceClient gsClient;
    private final String API_KEY;

    public GiphyLookerService(DevelopersGiphyProperties developersGiphyProperties, GiphyServiceClient gsClient) {
        this.developersGiphyProperties = developersGiphyProperties;
        this.gsClient = gsClient;
        this.API_KEY=developersGiphyProperties.getApiKey();
    }

    public String searchGiphy(String giphyTag) {
        String giphyUrl;
        try {
            giphyUrl = gsClient.getGiphyJson(API_KEY, giphyTag).getData().getImage_original_url();
        } catch (Exception e) {
            throw new RuntimeException(MESSAGE_ERROR_GET_GIPHY + e.getMessage());
        }
        return giphyUrl;
    }
}
