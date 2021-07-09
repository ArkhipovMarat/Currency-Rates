package com.example1.feignclient;

import com.example1.dto.giphydata.GiphyDataDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "gyphy-service", url = "${developersgiphy.api_url}", primary = false)
public interface GiphyServiceClient {
    String API_PATH = "";

    @GetMapping(API_PATH)
    GiphyDataDto getGiphyJson(@RequestParam("api_key") String apiKey, @RequestParam("tag") String tag);
}
