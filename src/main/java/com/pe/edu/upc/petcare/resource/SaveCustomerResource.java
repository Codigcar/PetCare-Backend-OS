package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class SaveCustomerResource {

    private Long id;
    private String firstname;
    private String lastname;
    private String document;
    private String email;
    private String Phone;
    private String Age;
}
