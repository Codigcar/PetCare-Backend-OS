package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class SavePersonProfileResource {

    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String phone;
    private String age;
}
