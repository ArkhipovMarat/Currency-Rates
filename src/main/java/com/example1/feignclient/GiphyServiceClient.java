package com.example1.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="gyphy-service", url = "${giphy.api_url}")
public interface GiphyServiceClient {
    String API_PATH = "";

    @GetMapping(API_PATH)
    String getGiphyJson(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);
}
