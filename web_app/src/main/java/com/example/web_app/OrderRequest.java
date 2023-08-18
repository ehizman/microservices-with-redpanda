package com.example.web_app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequest {
    private String customerCardDetails;
    private String deliveryDetails;
}
