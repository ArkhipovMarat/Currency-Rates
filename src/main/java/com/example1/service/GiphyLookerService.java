package com.example1.service;

import com.example1.feignclient.GiphyServiceClient;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private GiphyServiceClient gsClient;

    private final ObjectMapper objectMapper;

    public GiphyLookerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String searchGiphy(String tag) {
        JsonNode jsonNode = null;
        try {
            String json = gsClient.getGiphyJson(apiKey, tag);
            jsonNode = objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonNode.get(jsonNod).get(jsonKey).asText();
    }
}
