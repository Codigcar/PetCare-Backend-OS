package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResource {

    private String number;
    private String name;
    private String cvv_number;
    private String expiry_date;

}
