package com.assessment.ProductManagementSystem.repository;



import com.assessment.ProductManagementSystem.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
    boolean existsByName(String name);
}

