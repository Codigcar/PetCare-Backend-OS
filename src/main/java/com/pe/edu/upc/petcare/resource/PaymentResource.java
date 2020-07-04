package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResource {
    private Long id;
    private Long cardNumber;
    private String name;
    private Integer cvvNumber;
    private String expiryDate;
}
