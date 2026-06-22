package com.fundocorp.backend.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI fundocorpOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("FundoCorp API")
                        .version("1.0.0")
                        .description("API REST para el sistema de monitoreo de riego FundoCorp"));
    }
}
