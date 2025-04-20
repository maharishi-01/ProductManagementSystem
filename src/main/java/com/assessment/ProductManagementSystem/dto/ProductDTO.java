package com.assessment.ProductManagementSystem.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private String description;

    @NotEmpty(message = "Product name cannot be empty")
    private String name;

    @NotNull(message = "Product price cannot be null")
    @Positive(message = "Product price must be greater than zero")
    private BigDecimal price;

    @NotNull(message = "Product quantity cannot be null")
    @Positive(message = "Product quantity must be greater than zero")
    private Integer quantity;

}