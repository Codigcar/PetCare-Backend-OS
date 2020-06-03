package com.pe.edu.upc.petcare.resource.save;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class SaveAvailabilityResource {
    private String dateAvailability;
    private String startTime;
    private String endTime;
}
