package com.example.product.facade;

import com.example.product.dto.ProductRequestDto;
import com.example.product.dto.ProductResponseDto;
import com.example.product.dto.ProductResponsemsgDto;
import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductFacade {
@Autowired
    private ProductRepository productRepository;
    public ProductResponsemsgDto addProduct(ProductRequestDto request) {
        if (request.getName() == null || request.getName().isEmpty()||request.getName().matches("\\d+")) {
            return new ProductResponsemsgDto( "Name is required.");
        }
        if (request.getDescription() == null || request.getDescription().isEmpty()||request.getDescription().matches("\\d+")) {
            return new ProductResponsemsgDto( "Description is required.");
        }
        if (request.getCategory() == null || request.getCategory().isEmpty()||request.getCategory().matches("\\d+")) {
            return new ProductResponsemsgDto( "Category is required.");
        }
        if (request.getPrice() == null) {
            return new ProductResponsemsgDto( "Price is required.");
        }
        if (request.getStockQuantity() == null) {
            return new ProductResponsemsgDto( "Stock Quantity is required.");
        }

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product = productRepository.save(product);

        return new ProductResponsemsgDto("Product created successfully.");
    }
    public ProductResponsemsgDto updateProduct(Long productId, ProductRequestDto request) {
        try {
            Product product = productRepository.findById(productId);
            if (request.getName() == null || request.getName().isEmpty()||request.getName().matches("\\d+")) {
                return new ProductResponsemsgDto( "Name is required.");
            }
            if (request.getDescription() == null || request.getDescription().isEmpty()||request.getDescription().matches("\\d+")) {
                return new ProductResponsemsgDto( "Description is required.");
            }
            if (request.getCategory() == null || request.getCategory().isEmpty()||request.getCategory().matches("\\d+")) {
                return new ProductResponsemsgDto( "Category is required.");
            }
            if (request.getPrice() == null) {
                return new ProductResponsemsgDto( "Price is required.");
            }
            if (request.getStockQuantity() == null) {
                return new ProductResponsemsgDto( "Stock Quantity is required.");
            }
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setCategory(request.getCategory());
            product.setPrice(request.getPrice());
            product.setStockQuantity(request.getStockQuantity());
            product = productRepository.save(product);
            return new ProductResponsemsgDto( "Product updated successfully.");
        } catch (Exception e) {
            return new ProductResponsemsgDto( "Product update failed due to an unexpected error.");
        }
    }
}
