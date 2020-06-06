package com.pe.edu.upc.petcare.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionPlanResource {

    private Long id;

    private String name;

    private String description;

    private int duration;

    private Double price;
}
