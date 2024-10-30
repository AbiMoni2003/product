package com.example.product.repository;


import com.example.product.entity.Product;

import java.util.List;

public interface ProductRepository {

    Product save(Product product);

    Product findById(Long id);

    void delete(Product product);

    List<Product> findAll(int page, int size, String sortField, String sortDirection);

    List<Product> searchByNameOrCategory(String name, String category, int page, int size, String sortField, String sortDirection);
}
