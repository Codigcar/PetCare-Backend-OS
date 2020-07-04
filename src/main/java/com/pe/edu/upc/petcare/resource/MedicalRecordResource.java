package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalRecordResource {

    private Long Id ;
    private String description;
    private String type;
    private String action;

}
