package com.pe.edu.upc.petcare.resource.save;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class SavePaymentResource {

    private Long cardNumber;
    private String cardName;
    private Integer cvvNumber;
    private String expiryDate;
}
