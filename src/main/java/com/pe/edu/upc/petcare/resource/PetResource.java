package com.pe.edu.upc.petcare.resource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetResource {
    private Long id;
    private String name;
    private String age;
    private String breed;
    private String photo;
    private String gender;
}