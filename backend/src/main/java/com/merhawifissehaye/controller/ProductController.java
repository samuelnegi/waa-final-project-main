package com.merhawifissehaye.controller;

import com.merhawifissehaye.dto.CreateProductDto;
import com.merhawifissehaye.model.Product;
import com.merhawifissehaye.model.User;
import com.merhawifissehaye.repository.UserRepository;
import com.merhawifissehaye.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/products")
public class ProductController {
    ProductService productService;
    private UserRepository userRepository;

    @GetMapping("/{id}")
    @ResponseBody
    public Product getProduct(@PathVariable("id") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("")
    @ResponseBody
    public Page<Product> getProducts(@RequestParam(name = "q", required = false) String query, @RequestParam(name = "page", required = false) int page) {
        return productService.getProducts(query, page, 10);
    }

    @PostMapping("")
    @ResponseBody
    public ResponseEntity<Product> createProduct(@Valid @RequestBody CreateProductDto productDto, @AuthenticationPrincipal String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        var product = productDto.toProduct();
        product.setOwner(user);
        product = productService.createProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
}
