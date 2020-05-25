package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SavePaymentResource {

    private String cardNumber;
    private String cardName;
    private String cvvNumber;
    private String expiryDate;
}
