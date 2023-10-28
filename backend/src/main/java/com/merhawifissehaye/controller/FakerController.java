package com.merhawifissehaye.controller;

import com.merhawifissehaye.model.Product;
import com.merhawifissehaye.service.FakerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/faker")
public class FakerController {
    FakerService fakerService;

    @PostMapping("/products")
    Iterable<Product> createProducts() {
        return fakerService.createProducts();
    }
}
