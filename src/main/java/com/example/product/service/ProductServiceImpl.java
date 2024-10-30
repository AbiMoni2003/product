package com.example.product.service;


import com.example.product.dto.ProductRequestDto;
import com.example.product.dto.ProductResponseDto;
import com.example.product.dto.ProductResponsemsgDto;
import com.example.product.entity.Product;
import com.example.product.facade.ProductFacade;
import com.example.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductFacade productFacade;

    @Override
    @Transactional
    public ProductResponsemsgDto addProduct(ProductRequestDto request) {
        return productFacade.addProduct(request);
    }


    @Override
    @Transactional
    public ProductResponsemsgDto updateProduct(Long productId, ProductRequestDto request) {
      return productFacade.updateProduct(productId,request);
    }

    @Override
    @Transactional
    public ProductResponsemsgDto deleteProduct(Long productId) {
        Product product = productRepository.findById(productId);
        if (product == null) {
            return new ProductResponsemsgDto( "Product with ID " + productId + " not found.");
        }
        productRepository.delete(product);
        return new ProductResponsemsgDto( "Product deleted successfully.");
    }

    @Override
    public ProductResponseDto getProductById(Long productId) {
        Product product = productRepository.findById(productId);
        if (product == null) {
            return new ProductResponseDto("Product with ID " + productId + " not found.");
        }
        return mapToResponseDto(product);
    }

    @Override
    public List<ProductResponseDto> getProducts(String name, String category, int page, int size, String sortField, String sortDirection) {
        List<Product> products = productRepository.searchByNameOrCategory(name, category, page, size, sortField, sortDirection);
        return products.stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    private ProductResponseDto mapToResponseDto(Product product) {
        return new ProductResponseDto(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getCategory(),
                product.getPrice(),
                product.getStockQuantity()
        );
    }
}
