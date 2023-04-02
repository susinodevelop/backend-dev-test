package com.susinodevelop.backendtest.similarproduct.infrastructure.inputAdapter;

import com.susinodevelop.backendtest.similarproduct.application.SimilarProductService;
import com.susinodevelop.backendtest.similarproduct.domain.ProductDetail;
import com.susinodevelop.backendtest.similarproduct.infrastructure.inputPort.ProductApi;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class ProductApiController implements ProductApi {

    private final SimilarProductService service;

    @Autowired
    public ProductApiController(final SimilarProductService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Set<ProductDetail>> getProductSimilar(@Parameter(name = "productId", description = "Provided product id", required = true, in = ParameterIn.PATH) @PathVariable("productId") String productId) {
        Set<ProductDetail> result = this.service.getProductSimilar(productId);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
}
