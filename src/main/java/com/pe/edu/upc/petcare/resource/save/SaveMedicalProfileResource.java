package com.pe.edu.upc.petcare.resource.save;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class SaveMedicalProfileResource {

    private String Name ;

    private String Weight ;

    private String Height ;

    private String Lenght ;

    private String Eyes ;

    private String Breed ;

    private String Gender ;

    private String Color;

    private String Description ;

    private String Photo ;

    private String Age ;
}
