package com.merhawifissehaye.service;

import com.merhawifissehaye.model.Product;
import com.merhawifissehaye.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.print.Pageable;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    ProductRepository productRepository;

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public Page<Product> getProducts(String name, int pageNumber, int pageSize) {
        Sort sort = Sort.by("name").ascending();
        PageRequest paging = PageRequest.of(pageNumber, pageSize, sort);
        if (name != null && !name.isEmpty()) return productRepository.findByNameContaining(name, paging);
        return productRepository.findAll(paging);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
}
