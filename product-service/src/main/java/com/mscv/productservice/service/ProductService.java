package com.mscv.productservice.service;

import com.mscv.productservice.dto.ProductRequest;
import com.mscv.productservice.dto.ProductResponse;
import com.mscv.productservice.model.Product;
import com.mscv.productservice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .id(UUID.randomUUID().toString())
                .build();

        productRepository.save(product);
        log.info("Product {} has been created succesfully", product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::mapProductToProductResponse)
                .collect(toList());
    }

    private ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .id(UUID.randomUUID().toString())
                .build();
    }
}
