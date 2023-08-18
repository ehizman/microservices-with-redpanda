package com.example.web_app;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class MockPaymentService {
    private final Random random;

    public MockPaymentService() {
        this.random = new Random();
    }

    public ResponseEntity<?> processPayment(String cardDetails){
        if (random.nextInt(2) == 1){
            return new ResponseEntity<>("payment successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("payment failed", HttpStatus.BAD_REQUEST);
        }
    }
}
