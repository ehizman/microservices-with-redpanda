package com.example.web_app;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class WebController {
    private final EcommerceService ecommerceService;

    public WebController(EcommerceService ecommerceService) {
        this.ecommerceService = ecommerceService;
    }

    @GetMapping()
    public String entryPoint(){
        return "Webapp";
    }
    @PostMapping("")
    public ResponseEntity<?> initiateOrder(@RequestParam("id") String productId, @RequestParam("q") Integer quantity, @RequestBody OrderRequest orderRequest){
        return ecommerceService.initiateOrder(productId, quantity, orderRequest);
    }
}
