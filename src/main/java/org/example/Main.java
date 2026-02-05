package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // 開啟快取功能，配合你在 Service 寫的 @Cacheable
public class Main {
    public static void main(String[] args) {
        // 這行是啟動 Spring Boot 的核心，它會掃描你建好的 Controller 和 Service
        SpringApplication.run(Main.class, args);

        System.out.println("=======================================");
        System.out.println("   天氣 App 後端已啟動！路徑：http://localhost:8080");
        System.out.println("=======================================");
    }
}