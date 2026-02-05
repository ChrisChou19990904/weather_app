package org.example.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDTO {
    private String cityName;
    private Double tempC;
    private String conditionText;
    private String iconUrl;
    private Integer humidity;
    private String observationTime; // 格式化後的時間
}
