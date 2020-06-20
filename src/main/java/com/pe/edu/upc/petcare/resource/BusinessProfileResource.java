package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessProfileResource {
    private Long id;
    private String name;
    private String lastName;
    private Long document;
    private String email;
    private Long phone;
    private int age;
    private boolean owner;
}
