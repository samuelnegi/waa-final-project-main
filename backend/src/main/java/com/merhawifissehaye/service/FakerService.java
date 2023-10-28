package com.merhawifissehaye.service;

import com.github.javafaker.Faker;
import com.merhawifissehaye.model.Product;
import com.merhawifissehaye.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FakerService {
    ProductRepository productRepository;

    public Iterable<Product> createProducts() {
        if (productRepository.count() > 0) return List.of();
        Faker faker = new Faker(Locale.US);
        List<Product> products = Arrays.stream(new long[100]).mapToObj(id -> Product.builder().id(id).
                name(faker.commerce().productName())
                .description(faker.lorem().sentence(10))
                .build()).collect(Collectors.toList());
        return productRepository.saveAll(products);
    }
}
