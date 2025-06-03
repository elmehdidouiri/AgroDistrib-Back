package org.example.miniprojetback.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("*") // Autorise toutes les origines
                        .allowedMethods("*") // Autorise toutes les méthodes HTTP
                        .allowedHeaders("*") // Autorise tous les en-têtes
                        .allowCredentials(false); // Désactive les cookies
            }
        };
    }
}