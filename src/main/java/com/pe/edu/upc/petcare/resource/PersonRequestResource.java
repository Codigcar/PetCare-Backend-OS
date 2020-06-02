package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PersonRequestResource {

    private Long id;

    private Date createdAt;

    private String startTime;

    private String endTime;

    private Boolean status;
}
