package com.example1.configuration;

import com.example1.dto.DevelopersGiphyProperties;
import com.example1.dto.OpenExchangeRatesProperties;
import com.example1.interceptor.EndPointValidationInterceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ApplicationConfig implements WebMvcConfigurer {
    private final EndPointValidationInterceptor endPointValidationInterceptor;

    public ApplicationConfig(EndPointValidationInterceptor endPointValidationInterceptor) {
        this.endPointValidationInterceptor = endPointValidationInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(endPointValidationInterceptor);
    }

    @Bean
    @ConfigurationProperties("openexchangerates")
    OpenExchangeRatesProperties openExchangeRatesProperties() {
        return new OpenExchangeRatesProperties();
    }

    @Bean
    @ConfigurationProperties("developersgiphy")
    DevelopersGiphyProperties developersGiphyProperties() {
        return new DevelopersGiphyProperties();
    }
}
