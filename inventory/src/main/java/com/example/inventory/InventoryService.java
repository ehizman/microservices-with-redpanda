package com.example.inventory;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InventoryService {
    private final ProductRepository productRepository;

    public InventoryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public synchronized String createOrder(String productId, int quantity) throws ProductException {
        Product product =
                productRepository.findProductByProductId(productId)
                        .orElseThrow(() -> new ProductException("product not in stock"));

        if (product.getStockLevel() < quantity) {
            throw new ProductException("insufficient product quantity");
        }

        product.setStockLevel(product.getStockLevel() - quantity);
        productRepository.save(product);

        return "product ordered successfully";
    }

    public Product createProduct(CreateRequest request) {
        Product product = new Product(
                request.getProductId(),
                request.getStockLevel(),
                request.getName(),
                BigDecimal.valueOf(request.getPrice()),
                request.getDescription()
        );
        productRepository.save(product);
        return product;
    }
}
