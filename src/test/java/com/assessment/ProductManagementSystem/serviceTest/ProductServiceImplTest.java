package com.assessment.ProductManagementSystem.serviceTest;


import com.assessment.ProductManagementSystem.dto.ProductDTO;
import com.assessment.ProductManagementSystem.entity.Product;
import com.assessment.ProductManagementSystem.exception.ProductManagementException;
import com.assessment.ProductManagementSystem.repository.ProductRepository;
import com.assessment.ProductManagementSystem.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product;
    private ProductDTO productDTO;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id("abc123")
                .name("Test Product")
                .description("A test product")
                .price(BigDecimal.valueOf(99.99))
                .quantity(3)
                .build();

        productDTO = ProductDTO.builder()
                .name("Test Product")
                .description("A test product")
                .price(BigDecimal.valueOf(99.99))
                .quantity(3)
                .build();
    }

    @Test
    void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.createProduct(productDTO);

        assertNotNull(savedProduct);
        assertEquals(productDTO.getName(), savedProduct.getName());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testGetProductById_Success() throws ProductManagementException {
        when(productRepository.findById("abc123")).thenReturn(Optional.of(product));

        Product foundProduct = productService.getProductById("abc123");

        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getName());
        verify(productRepository, times(1)).findById("abc123");
    }

    @Test
    void testGetProductById_NotFound() {
        when(productRepository.findById("xyz999")).thenReturn(Optional.empty());

        ProductManagementException exception = assertThrows(ProductManagementException.class, () ->
                productService.getProductById("xyz999"));

        assertTrue(exception.getMessage().contains("Product id:- xyz999 not found!!"));
        verify(productRepository, times(1)).findById("xyz999");
    }

    @Test
    void testGetAllProductsWithPagination() {
        Pageable pageable = PageRequest.of(0, 5, Sort.by("name").ascending());
        Page<Product> productPage = new PageImpl<>(List.of(product));

        when(productRepository.findAll(pageable)).thenReturn(productPage);

        Page<Product> result = productService.getAllProducts(pageable);

        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Test Product", result.getContent().get(0).getName());

        verify(productRepository, times(1)).findAll(pageable);
    }

    @Test
    void testUpdateProduct_Success() throws ProductManagementException {
        when(productRepository.findById("abc123")).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDTO updatedDTO = ProductDTO.builder()
                .name("Updated Product")
                .description("Updated description")
                .price(BigDecimal.valueOf(199.99))
                .quantity(3)
                .build();

        Product updatedProduct = productService.updateProduct("abc123", updatedDTO);

        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals("Updated description", updatedProduct.getDescription());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void testDeleteProduct_Success() throws ProductManagementException {
        when(productRepository.findById("abc123")).thenReturn(Optional.of(product));
        doNothing().when(productRepository).deleteById("abc123");

        assertDoesNotThrow(() -> productService.deleteProduct("abc123"));
        verify(productRepository, times(1)).deleteById("abc123");
    }

    @Test
    void testDeleteProduct_NotFound() {
        when(productRepository.findById("xyz999")).thenReturn(Optional.empty());

        ProductManagementException exception = assertThrows(ProductManagementException.class, () ->
                productService.deleteProduct("xyz999"));

        assertTrue(exception.getMessage().contains("Product id:- xyz999 not found!!"));
        verify(productRepository, never()).deleteById(anyString());
    }
}
