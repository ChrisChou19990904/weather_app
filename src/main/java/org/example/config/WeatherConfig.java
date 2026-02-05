package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class WeatherConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    // 未來如果你想自定義快取過期時間（TTL），也會寫在這裡
}
