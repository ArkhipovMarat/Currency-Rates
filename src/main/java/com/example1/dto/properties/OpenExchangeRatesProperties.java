package com.example1.dto.properties;

import lombok.Data;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;

@Data
@Validated
public class OpenExchangeRatesProperties {
    @NotBlank(message = "api url is blank")
    private String apiUrl;
    @NotBlank(message = "api key name is blank")
    private String apiKeyName;
    @NotBlank(message = "api key is blank")
    private String apiKey;
    @NotBlank(message = "base currency is blank")
    private String base;
}
