package com.susinodevelop.backendtest.similarproduct.infrastructure.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI apiInfo(
            @Value("${app.spring-doc.version}") String springDocVersion,
            @Value("${app.version}") String projectVersion
    ) {
        return new OpenAPI()
                .openapi(springDocVersion)
                .info(
                        new Info()
                                .title("SimilarProducts")
                                .description("Api to find similar product than provided ones")
                                .version(projectVersion)
                );
    }

}