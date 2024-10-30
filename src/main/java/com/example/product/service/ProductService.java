package com.example.product.service;


import com.example.product.dto.ProductRequestDto;
import com.example.product.dto.ProductResponseDto;
import com.example.product.dto.ProductResponsemsgDto;

import java.util.List;

public interface ProductService {

    ProductResponsemsgDto addProduct(ProductRequestDto request);

    ProductResponsemsgDto updateProduct(Long productId, ProductRequestDto request);

    ProductResponsemsgDto deleteProduct(Long productId);

    ProductResponseDto getProductById(Long productId);

    List<ProductResponseDto> getProducts(String name, String category, int page, int size, String sortField, String sortDirection);
}
