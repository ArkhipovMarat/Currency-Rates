package com.example1.service;

import com.example1.feignclient.GiphyServiceClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GiphyLookerService {
    @Value("${giphy.api_key:null}")
    private String apiKey;

    @Value("${giphy.json_key:image_url}")
    private String jsonKey;

    @Value("${giphy.json_node:data}")
    private String jsonNod;

    private final GiphyServiceClient gsClient;

    private final ObjectMapper objectMapper;

    public GiphyLookerService(ObjectMapper objectMapper, GiphyServiceClient gsClient) {
        this.objectMapper = objectMapper;
        this.gsClient = gsClient;
    }

    public String searchGiphy(String tag) {
        JsonNode jsonNode;
        String giphyUrl;
        try {
            String giphyJson = gsClient.getGiphyJson(apiKey, tag);
            jsonNode = objectMapper.readTree(giphyJson);
            giphyUrl = jsonNode.get(jsonNod).get(jsonKey).asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't get response from giphy client! Please check request is valid");
        } catch (RuntimeException e) {
            throw new RuntimeException("Can't get giphy! Please check properties " +
                    "'jsonNod', 'jsonKey', 'api_key' are valid");
        }
        return giphyUrl;
    }
}
