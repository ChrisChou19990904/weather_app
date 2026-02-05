package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "search_history")
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cityName;

    private LocalDateTime searchTime;

    // 建立一個建構子方便使用
    public SearchHistory() {}

    public SearchHistory(String cityName, LocalDateTime searchTime) {
        this.cityName = cityName;
        this.searchTime = searchTime;
    }
}
