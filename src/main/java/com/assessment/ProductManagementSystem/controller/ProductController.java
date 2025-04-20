package com.assessment.ProductManagementSystem.controller;

import com.assessment.ProductManagementSystem.dto.ProductDTO;
import com.assessment.ProductManagementSystem.entity.Product;
import com.assessment.ProductManagementSystem.exception.ProductManagementException;
import com.assessment.ProductManagementSystem.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details.")
    public ResponseEntity<Product> create(@Valid @RequestBody ProductDTO dto) {
        log.info("Creating new product: {}", dto.getName());
        Product created = productService.createProduct(dto);
        log.info("Product created with ID: {}", created.getId());
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its ID.")
    public ResponseEntity<Product> getById(@PathVariable String id) throws ProductManagementException {
        log.info("Fetching product with ID: {}", id);
        Product product = productService.getProductById(id);
        log.info("Product retrieved: {}", product.getName());
        return ResponseEntity.ok(product);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieves a list of products with pagination and sorting.")
    public Page<Product> getAllProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir) {

        log.info("Fetching products with page: {}, size: {}, sorted by: {} in {} order",
                page, size, sortBy, sortDir);
        Pageable pageable = PageRequest.of(
                page,
                size,
                sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending()
        );
        return productService.getAllProducts(pageable);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing product", description = "Updates the details of an existing product by ID.")
    public ResponseEntity<Product> update(@PathVariable String id, @Valid @RequestBody ProductDTO dto) throws ProductManagementException {
        log.info("Updating product with ID: {}", id);
        Product updated = productService.updateProduct(id, dto);
        log.info("Product updated with ID: {}", updated.getId());
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Deletes a product by its ID.")
    public ResponseEntity<String> delete(@PathVariable String id) throws ProductManagementException {
        log.info("Deleting product with ID: {}", id);
        productService.deleteProduct(id);
        log.info("Product with ID: {} deleted successfully", id);
        return ResponseEntity.ok("Deleted successfully");
    }
}


