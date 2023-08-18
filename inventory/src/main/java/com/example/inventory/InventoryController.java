package com.example.inventory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class InventoryController { private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody CreateRequest request) {
        Product product = inventoryService.createProduct(request);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<?> orderProduct(@RequestParam("productId") String productId,
                                          @RequestParam("quantity") int quantity) {
        try {
            String response = inventoryService.createOrder(productId, quantity);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (ProductException productException) {
            return new ResponseEntity<>(productException.getMessage(), HttpStatus.OK);
        }
    }
}
