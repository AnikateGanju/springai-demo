package com.example.ai.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("Spring AI Enterprise Demo API")
                        .version("1.0.0")
                        .description("Sample enterprise-grade Spring AI + OpenAI + RAG"));
    }
}
