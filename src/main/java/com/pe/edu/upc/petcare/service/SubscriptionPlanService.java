package com.pe.edu.upc.petcare.service;


import com.pe.edu.upc.petcare.model.SubscriptionPlan;

import java.util.List;

public interface SubscriptionPlanService {

    SubscriptionPlan createSubscriptionPlan (SubscriptionPlan subscriptionPlan);
    List<SubscriptionPlan> getAllSubscriptionPlan();
}
