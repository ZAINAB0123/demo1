package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI demo1OpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Demo1 CRM API")
                        .version("1.0.0")
                        .description("REST API for customer and deal management")
                        .contact(new Contact()
                                .name("Demo1 Team")));
    }
}
