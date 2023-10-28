package com.merhawifissehaye.service;


import com.merhawifissehaye.model.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    public Product getProductById(Long id);

    Page<Product> getProducts(String name, int pageNumber, int pageSize);

    Product createProduct(Product product);
}
