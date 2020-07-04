package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AvailabilityResource {

    private Long id;
    private String dateAvailability;
    private String startTime;
    private String endTime;
}
