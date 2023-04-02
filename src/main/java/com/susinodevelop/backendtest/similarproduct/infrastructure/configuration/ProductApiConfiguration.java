package com.susinodevelop.backendtest.similarproduct.infrastructure.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app.configuration.product-service")
@Getter
@Setter
public class ProductApiConfiguration {

    String schema;
    String host;
    Integer port;
    String similarIdsEndpoint;
    String productEndpoint;

}
