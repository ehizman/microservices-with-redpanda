package com.example.inventory;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateRequest {
    private String productId;
    private String name;
    private Integer stockLevel;
    private Double price;
    private String description;
}
