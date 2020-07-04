package com.pe.edu.upc.petcare.resource.save;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
public class SaveMedicalProfileResource {

    private String name ;

    private String weight ;

    private String height ;

    private String length ;

    private String eyes ;

    private String breed ;

    private String gender ;

    private String color;

    private String description ;

    private String photo ;

    private Integer age ;
}
