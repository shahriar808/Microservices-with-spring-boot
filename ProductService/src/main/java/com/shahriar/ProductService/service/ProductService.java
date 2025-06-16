package com.shahriar.ProductService.service;

import com.shahriar.ProductService.model.ProductRequest;
import com.shahriar.ProductService.model.ProductResponse;

public interface ProductService {
    Long addProduct(ProductRequest productRequest);

    ProductResponse getProductById(Long productId);

    void reduceQuantity(long productId, long quantity);
}
