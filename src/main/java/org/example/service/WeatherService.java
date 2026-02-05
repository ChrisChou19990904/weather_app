package org.example.service;

import org.example.dto.WeatherDTO;
import org.example.entity.SearchHistory;
import org.example.repository.SearchHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.mock.mode:true}")
    private boolean isMockMode;

    @Autowired
    private RestTemplate restTemplate;

    // 1. 補上 Repository 的注入
    @Autowired
    private SearchHistoryRepository searchHistoryRepository;

    @Cacheable(value = "weatherCache", key = "#city")
    public WeatherDTO getWeather(String city) {
        WeatherDTO dto;
        if (isMockMode) {
            dto = generateMockDTO(city);
        } else {
            dto = fetchRealWeatherData(city);
        }

        // 2. 存入資料庫：只要有抓到資料，就存一筆歷史紀錄
        if (dto != null) {
            searchHistoryRepository.save(new SearchHistory(dto.getCityName(), LocalDateTime.now()));
        }

        return dto;
    }

    // 3. 補上這個被 Controller 呼叫的方法
    public List<SearchHistory> getRecentHistory() {
        return searchHistoryRepository.findTop5ByOrderBySearchTimeDesc();
    }

    private WeatherDTO fetchRealWeatherData(String city) {
        String url = String.format("http://api.weatherapi.com/v1/current.json?key=%s&q=%s&lang=zh_tw", apiKey, city);

        try {
            // 抓取原始 JSON 數據
            JsonNode root = restTemplate.getForObject(url, JsonNode.class);

            // 將 API 的複雜 JSON 對應到你的乾淨 DTO
            return new WeatherDTO(
                    root.path("location").path("name").asText(),
                    root.path("current").path("temp_c").asDouble(),
                    root.path("current").path("condition").path("text").asText(),
                    "https:" + root.path("current").path("condition").path("icon").asText(),
                    root.path("current").path("humidity").asInt(),
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            );
        } catch (Exception e) {
            System.err.println("API 請求失敗: " + e.getMessage());
            return generateMockDTO(city + " (抓取失敗)");
        }
    }

    private WeatherDTO generateMockDTO(String city) {
        return new WeatherDTO(city + " (Mock)", 25.0, "多雲", "//cdn.weatherapi.com/weather/64x64/day/116.png", 60, "2026-02-04 22:00");
    }
}
