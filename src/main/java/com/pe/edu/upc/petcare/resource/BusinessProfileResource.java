package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessProfileResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String phone;
    private String age;
}
