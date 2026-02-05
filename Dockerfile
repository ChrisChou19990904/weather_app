# 階段 1：使用 Maven 進行編譯與打包
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# 階段 2：使用 JDK 執行程式
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# 從 build 階段複製 jar 檔進來
COPY --from=build /app/target/weather_app-0.0.1-SNAPSHOT.jar app.jar
# 執行
ENTRYPOINT ["java", "-jar", "app.jar"]