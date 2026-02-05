package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WeatherConfig implements WebMvcConfigurer{

    // 1. 保留原本的 RestTemplate (用來抓取天氣資料)
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    // 2. 修正後的 CORS 設定 (直接實作介面方法，不會報 Override 錯誤)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:5173",
                        "https://ChrisChou19990904.github.io"
                )
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*");
    }
}
