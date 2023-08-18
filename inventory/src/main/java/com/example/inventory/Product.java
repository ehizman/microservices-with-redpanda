package com.example.inventory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Document(collection = "products")
@Getter
@Setter
public class Product {
    @Id
    private String id;
    @NotNull(message = "product id cannot be null")
    @NotBlank(message = "product id cannot be blank")
    private String productId;
    @Min(value = 1)
    private int stockLevel;
    @NotNull(message = "product name cannot be null")
    @NotBlank(message = "product name cannot be blank")
    @Indexed(unique = true)
    private String name;

    @NotNull(message = "product description cannot be null")
    @NotBlank(message = "product description cannot be blank")
    private String description;
    @PositiveOrZero(message = "product price cannot be negative")
    private BigDecimal productPrice;

    @NotNull(message = "creation time cannot be null")
    private LocalDateTime creationTime;

    private LocalDateTime updateTime;

    public Product(String productId, int stockLevel, String name, BigDecimal productPrice, String description) {
        this.productId = productId;
        this.stockLevel = stockLevel;
        this.name = name;
        this.productPrice = productPrice;
        this.description = description;
        this.creationTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return String.format("""
                        product_id: %s,
                        stock_level: %s,
                        name: %s,
                        price: %s,
                        creation_time: %s
                        """,
                productId, stockLevel, name,
                productPrice,
                creationTime);
    }
}
