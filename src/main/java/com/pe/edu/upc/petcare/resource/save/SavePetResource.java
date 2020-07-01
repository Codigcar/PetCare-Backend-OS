package com.pe.edu.upc.petcare.resource.save;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class SavePetResource {

    private String name;
    private Integer age;
    private String breed;
    private String photo;
    private String gender;
}
