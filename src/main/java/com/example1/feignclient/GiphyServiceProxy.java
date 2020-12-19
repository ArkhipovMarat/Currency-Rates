package com.example1.feignclient;

import com.example1.entity.Giphy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="gyphy-service", url = "${giphy.api_url}")
public interface GiphyServiceProxy {

    @GetMapping("?api_key={apiKey}&tag={tag}")
    public Giphy getGiphy(@PathVariable("apiKey") String apiKey, @PathVariable("tag") String tag);
}
