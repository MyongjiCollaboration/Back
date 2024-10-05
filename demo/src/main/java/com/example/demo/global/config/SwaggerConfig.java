package com.example.demo.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// http://localhost:8080/swagger-ui/index.html#/
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApI(){
        return new OpenAPI()
                .info(new Info()
                        .title("아웅다웅")
                        .version("v1.0.0")
                        .description("Aungdaung Swagger API")
                );
    }
}
