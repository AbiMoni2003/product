package com.example.product.controller;

import com.example.product.dto.ProductRequestDto;
import com.example.product.dto.ProductResponseDto;
import com.example.product.dto.ProductResponsemsgDto;
import com.example.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("createProduct")
    public ResponseEntity<ProductResponsemsgDto> addProduct(@RequestBody ProductRequestDto request) {
        ProductResponsemsgDto response = productService.addProduct(request);
        return ResponseEntity.ok(response);
    }
    @PutMapping("updateProduct/{productId}")
    public ResponseEntity<ProductResponsemsgDto> updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDto request) {
        ProductResponsemsgDto response = productService.updateProduct(productId, request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("deleteProduct/{productId}")
    public ResponseEntity<ProductResponsemsgDto> deleteProduct(@PathVariable Long productId) {
        ProductResponsemsgDto response = productService.deleteProduct(productId);
        return ResponseEntity.ok(response);
    }
    @GetMapping("getById/{productId}")
    public ResponseEntity<ProductResponseDto> getProductById(@PathVariable Long productId) {
        ProductResponseDto product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }
    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String category,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "price") String sortField,
        @RequestParam(defaultValue = "ASC") String sortDirection) {
        List<ProductResponseDto> products = productService.getProducts(name, category, page, size, sortField, sortDirection);
        return ResponseEntity.ok(products);
    }
}
