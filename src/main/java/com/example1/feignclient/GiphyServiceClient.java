package com.example1.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="gyphy-service", url = "${giphy.api_url}")
public interface GiphyServiceClient {
    @GetMapping("?api_key={apiKey}&tag={tag}")
    String getGiphyJson(@RequestParam("apiKey") String apiKey, @RequestParam("tag") String tag);
}
