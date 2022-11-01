package com.evision.archivalmanagementmsapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springArchivalManagementApi() {
        return new OpenAPI()
                .info(new Info().title("Archival Management API")
                        .description("Archival Management API used by Bank users.")
                        .version("v1.0.0"));
    }
}
