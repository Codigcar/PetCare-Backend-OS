package com.pe.edu.upc.petcare.resource.save;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SaveSubscriptionPlanResource {


    private String name;

    private String description;

    private int duration;

    private Double price;
}
