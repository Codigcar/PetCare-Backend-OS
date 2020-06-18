package com.pe.edu.upc.petcare.controller;

import com.pe.edu.upc.petcare.model.SubscriptionPlan;
import com.pe.edu.upc.petcare.resource.SubscriptionPlanResource;
import com.pe.edu.upc.petcare.resource.save.SaveSubscriptionPlanResource;
import com.pe.edu.upc.petcare.service.SubscriptionPlanService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<SubscriptionPlanResource>> getAllSubscriptionPlan(){
        List<SubscriptionPlan> subscriptionPlans = subscriptionPlanService.getAllSubscriptionPlan();
        List<SubscriptionPlanResource> subscriptionPlanResources = subscriptionPlans.stream().map(this::convertToResource).collect(Collectors.toList());
        return ResponseEntity.ok(subscriptionPlanResources);
    }

    private SubscriptionPlanResource convertToResource(SubscriptionPlan entity) {
        return mapper.map(entity, SubscriptionPlanResource.class);
    }

    private SubscriptionPlan convertToEntity(SaveSubscriptionPlanResource resource) {
        return mapper.map(resource, SubscriptionPlan.class);
    }


}
