package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.SubscriptionPlan;
import com.pe.edu.upc.petcare.resource.SubscriptionPlanResource;
import com.pe.edu.upc.petcare.resource.save.SaveSubscriptionPlanResource;
import com.pe.edu.upc.petcare.service.SubscriptionPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/subscription-plan")
public class SubscriptionPlanController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SubscriptionPlanService subscriptionPlanService;

    @PostMapping
    public SubscriptionPlanResource createSubscriptionPlan(@Valid @RequestBody SaveSubscriptionPlanResource resource){
        return convertToResource(subscriptionPlanService.createSubscriptionPlan(convertToEntity(resource)));
    }

    private SubscriptionPlanResource convertToResource(SubscriptionPlan entity) {
        return mapper.map(entity, SubscriptionPlanResource.class);
    }

    private SubscriptionPlan convertToEntity(SaveSubscriptionPlanResource resource) {
        return mapper.map(resource, SubscriptionPlan.class);
    }


}
