package com.susinodevelop.backendtest.similarproduct.infrastructure.outputPort;

import com.susinodevelop.backendtest.similarproduct.domain.ProductDetail;

import java.util.Set;

public interface RequestService {

    Set<String> getSimilarIds(String id);
    ProductDetail getProduct(String id);
}
