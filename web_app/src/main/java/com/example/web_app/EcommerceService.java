package com.example.web_app;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EcommerceService {
    private final RestTemplate restTemplate;
    private final MockPaymentService paymentService;

    public EcommerceService(MockPaymentService paymentService) {
        this.restTemplate = new RestTemplate();
        this.paymentService = paymentService;
    }

    public ResponseEntity<String> initiateOrder(String productId, int quantity, OrderRequest orderRequest) {
        ResponseEntity<String> response = createOrder(productId, quantity);

        // if product not in stock
        if (response.getStatusCode() != HttpStatus.OK){
            // return failure response
            return response;
        }
        // process payment
        ResponseEntity<?> responseFromPaymentService = paymentService.processPayment(orderRequest.getCustomerCardDetails());

        // if payment not successful
        if (response.getStatusCode() != HttpStatus.OK){
            // return payment failed response
            return response;
        }

        sendNotification(orderRequest.getDeliveryDetails());
        return new ResponseEntity<>("product order successful", HttpStatus.OK);
    }

    private void sendNotification(String deliveryDetails) {
        //send notification and payment receipt to customer email
    }

    private ResponseEntity<String> createOrder(String productId, int quantity) {
        final String INVENTORY_URL = "http://inventory:8001/product/order";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(INVENTORY_URL)
                // Add query parameter
                .queryParam("productId", productId)
                .queryParam("quantity", quantity);

        return restTemplate.exchange(builder.buildAndExpand().toUri(), HttpMethod.POST, requestEntity, String.class);
    }
}
