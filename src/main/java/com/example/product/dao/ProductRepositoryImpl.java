package com.example.product.dao;

import com.example.product.entity.Product;
import com.example.product.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            entityManager.persist(product);
            return product;
        } else {
            return entityManager.merge(product);
        }
    }

    @Override
    public Product findById(Long id) {
        return entityManager.find(Product.class, id);
    }

    @Override
    public void delete(Product product) {
        entityManager.remove(product);
    }

    @Override
    public List<Product> findAll(int page, int size, String sortField, String sortDirection) {
        String queryStr = "SELECT p FROM Product p ORDER BY p." + sortField + " " + sortDirection;
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }

    @Override
    public List<Product> searchByNameOrCategory(String name, String category, int page, int size, String sortField, String sortDirection) {
        String queryStr = "SELECT p FROM Product p WHERE p.name LIKE :name OR p.category LIKE :category ORDER BY p." + sortField + " " + sortDirection;
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        query.setParameter("name", "%" + name + "%");
        query.setParameter("category", "%" + category + "%");
        query.setFirstResult(page * size);
        query.setMaxResults(size);
        return query.getResultList();
    }
}
