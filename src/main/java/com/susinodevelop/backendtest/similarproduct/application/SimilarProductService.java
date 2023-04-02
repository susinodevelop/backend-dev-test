package com.susinodevelop.backendtest.similarproduct.application;

import com.susinodevelop.backendtest.similarproduct.domain.ProductDetail;
import com.susinodevelop.backendtest.similarproduct.infrastructure.outputPort.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SimilarProductService {

    final RequestService requestService;

    @Autowired
    public SimilarProductService(final RequestService requestService) {
        this.requestService = requestService;
    }

    public Set<ProductDetail> getProductSimilar(String productId) {
        Set<String> similarProductIds = this.requestService.getSimilarIds(productId);
        return similarProductIds.stream().map(this.requestService::getProduct).filter(Objects::nonNull).collect(Collectors.toSet());
    }

}
