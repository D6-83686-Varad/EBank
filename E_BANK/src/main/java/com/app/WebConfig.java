package com.app; // Adjust the package name as needed

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
    }
}

/*
 The WebConfig class is a Spring Boot configuration class that implements WebMvcConfigurer to customize the CORS settings for the application.
 By overriding the addCorsMappings method, it sets up rules to allow cross-origin requests from http://localhost:3000 with specific HTTP methods.
 This configuration is essential for managing cross-origin requests and ensuring that resources can be accessed by frontend applications during development.
*/