package com.example1.service;

import com.example1.dto.DevelopersGiphyProperties;
import com.example1.feignclient.GiphyServiceClient;
import org.springframework.stereotype.Component;

@Component
public class GiphyLookerService {
    private static final String MESSAGE_ERROR_GET_GIPHY =
            "Can't get giphy! Please check properties 'app_id', 'giphy_tag' are valid";

    private final DevelopersGiphyProperties developersGiphyProperties;
    private final GiphyServiceClient gsClient;

    public GiphyLookerService(DevelopersGiphyProperties developersGiphyProperties, GiphyServiceClient gsClient) {
        this.developersGiphyProperties = developersGiphyProperties;
        this.gsClient = gsClient;
    }

    public String searchGiphy(String giphyTag) {
        String apiKey = developersGiphyProperties.getApiKey();
        String giphyUrl;
        try {
            giphyUrl = gsClient.getGiphyJson(apiKey, giphyTag).getData().getImage_original_url();
        } catch (Exception e) {
            throw new RuntimeException(MESSAGE_ERROR_GET_GIPHY);
        }
        return giphyUrl;
    }
}
