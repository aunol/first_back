package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")  // 모든 출처 허용
                .allowedMethods("*")  // 모든 HTTP 메서드 허용
//                .allowedHeaders("*")  // 모든 헤더 허용
                .allowCredentials(true)
                .exposedHeaders(HttpHeaders.LOCATION);
    }
}
