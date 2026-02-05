# 階段 1：使用 Maven 打包
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
# 複製所有檔案
COPY . .
# 執行打包並跳過測試
RUN mvn clean package -DskipTests

# 階段 2：執行 Java 程式
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
# 注意：這裡的檔名必須完全對應你的 pom.xml 設定
COPY --from=build /app/target/weather_app-1.0-SNAPSHOT.jar app.jar
# 暴露 8080 端口 (Render 預設會偵測)
EXPOSE 8080
# 執行命令
ENTRYPOINT ["java", "-jar", "app.jar"]