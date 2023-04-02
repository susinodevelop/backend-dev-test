package com.susinodevelop.backendtest.similarproduct;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.susinodevelop.backendtest.similarproduct.domain.ProductDetail;
import com.susinodevelop.backendtest.similarproduct.infrastructure.configuration.ProductApiConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@SpringBootTest
@AutoConfigureMockMvc
public class SimilarProductTest {

    @Autowired
    ProductApiConfiguration configuration;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        UriBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(configuration.getSchema())
                .host(configuration.getHost())
                .port(configuration.getPort());

        builder.path(configuration.getSimilarIdsEndpoint());
        Mockito.when(restTemplate.getForEntity(builder.build("1"), String[].class))
                .thenReturn(ResponseEntity.ok(new String[]{"1"}));

        builder.replacePath(configuration.getProductEndpoint());
        Mockito.when(restTemplate.getForEntity(builder.build("1"), ProductDetail.class)).
                thenReturn(ResponseEntity.ok(new ProductDetail("1", "product", BigDecimal.valueOf(1), false)));
    }

    @Test
    @DisplayName("Get similar products - Status 200")
    void getSimilarProduct_200() throws Exception {
        UriBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(configuration.getSchema())
                .host(configuration.getHost())
                .port(configuration.getPort())
                .path(configuration.getSimilarIdsEndpoint());

        Mockito.when(restTemplate.getForEntity(builder.build("1"), String[].class))
                .thenReturn(ResponseEntity.ok(new String[]{"1"}));

        builder.replacePath(configuration.getProductEndpoint());
        Mockito.when(restTemplate.getForEntity(builder.build("1"), ProductDetail.class)).
                thenReturn(ResponseEntity.ok(new ProductDetail("1", "product", BigDecimal.valueOf(1), false)));

        mockMvc.perform(get("/product/1/similar")).andExpect(status().isOk());
    }

    @Test
    @DisplayName("Get similar products - ids request - Status 404")
    void getSimilarProduct_ids404() throws Exception {
        UriBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(configuration.getSchema())
                .host(configuration.getHost())
                .port(configuration.getPort())
                .path(configuration.getSimilarIdsEndpoint());

        Mockito.when(restTemplate.getForEntity(builder.build("5"), String[].class))
                .thenReturn(ResponseEntity.ok(new String[0]));

        mockMvc.perform(get("/product/5/similar")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Get similar products - product request - Status 404")
    void getSimilarProduct_product404() throws Exception {
        UriBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(configuration.getSchema())
                .host(configuration.getHost())
                .port(configuration.getPort())
                .path(configuration.getSimilarIdsEndpoint());

        Mockito.when(restTemplate.getForEntity(builder.build("1"), String[].class))
                .thenReturn(ResponseEntity.ok(new String[]{"1"}));

        builder.replacePath(configuration.getProductEndpoint());
        Mockito.when(restTemplate.getForEntity(builder.build("1"), ProductDetail.class)).
                thenReturn(ResponseEntity.notFound().build());

        mockMvc.perform(get("/product/1/similar")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Get similar products - Exception on similar product ids request")
    void getSimilarProduct_requestIdsException() throws Exception {
        UriBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(configuration.getSchema())
                .host(configuration.getHost())
                .port(configuration.getPort())
                .path(configuration.getSimilarIdsEndpoint());

        Mockito.when(restTemplate.getForEntity(builder.build("1"), String[].class))
                .thenThrow(ResourceAccessException.class);

        mockMvc.perform(get("/product/1/similar")).andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Get similar products - Exception on get product request")
    void getSimilarProduct_getProductException() throws Exception {
        UriBuilder builder = UriComponentsBuilder.newInstance()
                .scheme(configuration.getSchema())
                .host(configuration.getHost())
                .port(configuration.getPort())
                .path(configuration.getSimilarIdsEndpoint());

        Mockito.when(restTemplate.getForEntity(builder.build("1"), String[].class))
                .thenReturn(ResponseEntity.ok(new String[]{"1", "2", "3"}));

        builder.replacePath(configuration.getProductEndpoint());
        Mockito.when(restTemplate.getForEntity(builder.build("1"), ProductDetail.class)).
                thenThrow(ResourceAccessException.class);

        mockMvc.perform(get("/product/1/similar")).andExpect(status().isNotFound());
    }

}
