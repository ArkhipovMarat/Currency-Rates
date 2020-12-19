package com.example1.service;

import com.example1.feignclient.GiphyServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GiphyLookerService {
    @Value("${giphy.api_key}")
    private String apiKey = "";

    @Value("${giphy.json_key}")
    private String jsonKey = "";

    @Autowired
    private GiphyServiceProxy gsProxy;

    public Object searchGiphy(String tag) {
        return gsProxy.getGiphy(apiKey, tag).getData().get(jsonKey);
    }

}
