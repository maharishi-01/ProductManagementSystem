package com.assessment.ProductManagementSystem.service;


import com.assessment.ProductManagementSystem.dto.ProductDTO;
import com.assessment.ProductManagementSystem.entity.Product;
import com.assessment.ProductManagementSystem.exception.ProductManagementException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product getProductById(String id) throws ProductManagementException;
    Page<Product> getAllProducts(Pageable pageable);
    Product updateProduct(String id, ProductDTO productDTO) throws ProductManagementException;
    void deleteProduct(String id) throws ProductManagementException;
}

