package com.example1.dto.properties;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;

@Data
@Validated
public class DevelopersGiphyProperties {
    @NotBlank(message = "api url is blank")
    private String apiUrl;
    @NotBlank(message = "api key name is blank")
    private String apiKeyName;
    @NotBlank(message = "api key is blank")
    private String apiKey;
    @NotBlank(message = "giphy tag positive is blank")
    private String giphyTagPositive;
    @NotBlank(message = "giphy tag negative is blank")
    private String giphyTagNegative;
    @NotBlank(message = "giphy tag neutral is blank")
    private String giphyTagNeutral;
}
