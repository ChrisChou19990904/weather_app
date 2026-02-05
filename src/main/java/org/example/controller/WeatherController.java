package org.example.controller;

import org.example.dto.WeatherDTO;
import org.example.entity.SearchHistory;
import org.example.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api") // 注意：這裡是 /api
@CrossOrigin(origins = "*")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    // 最終確定的歷史路徑： /api/history
    @GetMapping("/history")
    public List<SearchHistory> getHistory() {
        return weatherService.getRecentHistory();
    }

    // 最終確定的天氣路徑： /api/weather/{city}
    @GetMapping("/weather/{city}")
    public WeatherDTO getCityWeather(@PathVariable String city) {
        return weatherService.getWeather(city);
    }
}
