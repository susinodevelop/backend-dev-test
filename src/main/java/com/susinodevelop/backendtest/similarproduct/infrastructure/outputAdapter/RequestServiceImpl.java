package com.susinodevelop.backendtest.similarproduct.infrastructure.outputAdapter;

import com.susinodevelop.backendtest.similarproduct.domain.ProductDetail;
import com.susinodevelop.backendtest.similarproduct.infrastructure.configuration.ProductApiConfiguration;
import com.susinodevelop.backendtest.similarproduct.infrastructure.outputPort.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.Set;

@Slf4j
@Service
public class RequestServiceImpl implements RequestService {

    final ProductApiConfiguration configuration;

    final RestTemplate restTemplate;

    @Autowired
    public RequestServiceImpl(
            final ProductApiConfiguration configuration,
            final RestTemplate restTemplate) {
        this.configuration = configuration;
        this.restTemplate = restTemplate;
    }

    @Override
    public Set<String> getSimilarIds(String id) {
        try {
            URI uri = UriComponentsBuilder.newInstance()
                    .scheme(configuration.getSchema())
                    .host(configuration.getHost())
                    .port(configuration.getPort())
                    .path(configuration.getSimilarIdsEndpoint())
                    .build(id);
            ResponseEntity<String[]> response = restTemplate.getForEntity(uri, String[].class);
            log.debug("Http request successfully - Method: {} Uri: {} Response: {}", HttpMethod.GET, uri, response);
            return response.getBody() != null ? Set.of(response.getBody()) : Collections.emptySet();
        } catch (Exception e) {
            log.error("Error on http request - Error: {}", e.getMessage(), e);
            return Collections.emptySet();
        }
    }

    @Override
    public ProductDetail getProduct(String id) {
        try {
            URI uri = UriComponentsBuilder.newInstance()
                    .scheme(configuration.getSchema())
                    .host(configuration.getHost())
                    .port(configuration.getPort())
                    .path(configuration.getProductEndpoint())
                    .build(id);
            ResponseEntity<ProductDetail> response = restTemplate.getForEntity(uri, ProductDetail.class);
            log.debug("Http request successfully - Method: {} Uri: {} Response: {}", HttpMethod.GET, uri, response);
            return response.getBody();
        } catch (Exception e) {
            log.error("Error on http request - Error: {}", e.getMessage(), e);
            return null;
        }
    }
}
