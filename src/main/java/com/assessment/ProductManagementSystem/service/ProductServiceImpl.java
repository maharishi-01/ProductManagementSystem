package com.assessment.ProductManagementSystem.service;


import com.assessment.ProductManagementSystem.dto.ProductDTO;
import com.assessment.ProductManagementSystem.entity.Product;
import com.assessment.ProductManagementSystem.exception.ProductManagementException;
import com.assessment.ProductManagementSystem.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    ProductServiceImpl(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(ProductDTO dto) {
        Product product = Product.builder()
                .description(dto.getDescription())
                .price(dto.getPrice())
                .name(dto.getName())
                .quantity(dto.getQuantity())
                .build();
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public Product getProductById(String id) throws ProductManagementException {
        return productRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Product not found with ID: {}", id);
                    return new ProductManagementException("Product with ID " + id + " not found");
                });
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product updateProduct(String id, ProductDTO dto) throws ProductManagementException {
        Product existingProduct = getProductById(id);
        existingProduct.setName(dto.getName());
        existingProduct.setDescription(dto.getDescription());
        existingProduct.setPrice(dto.getPrice());
        existingProduct.setQuantity(dto.getQuantity());
        Product updatedProduct = productRepository.save(existingProduct);
        return updatedProduct;
    }

    @Override
    public void deleteProduct(String id) throws ProductManagementException {
        getProductById(id);
        productRepository.deleteById(id);
    }
}


