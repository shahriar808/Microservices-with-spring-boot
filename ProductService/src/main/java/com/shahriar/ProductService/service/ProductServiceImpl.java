package com.shahriar.ProductService.service;

import com.shahriar.ProductService.entity.Product;
import com.shahriar.ProductService.exception.ProductServiceCustomException;
import com.shahriar.ProductService.model.ProductRequest;
import com.shahriar.ProductService.model.ProductResponse;
import com.shahriar.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Long addProduct(ProductRequest productRequest) {
        log.info("Adding product");
        Product product = Product.builder()
                .productName(productRequest.getProductName())
                .productPrice(productRequest.getProductPrice())
                .productQuantity(productRequest.getProductQuantity())
                .build();
        productRepository.save(product);
        log.info("Product added successfully");
        return product.getProductId();
    }

    @Override
    public ProductResponse getProductById(Long productId) {
        log.info("Get the product for productId : {}", productId);
        Product product
                = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();
        copyProperties(product, productResponse);
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reducing quantity : {} for productId : {} ", quantity, productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found", "PRODUCT_NOT_FOUND"));
        if (product.getProductQuantity() < quantity) {
            throw new ProductServiceCustomException("Insufficient product quantity", "INSUFFICIENT_QUANTITY");
        }
        product.setProductQuantity(product.getProductQuantity() - quantity);
        productRepository.save(product);
        log.info("Quantity reduced successfully for productId : {}", productId);
    }
}
