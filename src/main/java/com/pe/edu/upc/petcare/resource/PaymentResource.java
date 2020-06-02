package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResource {
    private Long id;
    private String cardNumber;
    private String name;
    private String cvvNumber;
    private String expiryDate;
}
