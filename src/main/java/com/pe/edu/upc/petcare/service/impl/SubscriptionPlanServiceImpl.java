package com.pe.edu.upc.petcare.service.impl;

import com.pe.edu.upc.petcare.model.SubscriptionPlan;
import com.pe.edu.upc.petcare.repository.SubscriptionPlanRepository;
import com.pe.edu.upc.petcare.service.SubscriptionPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    @Autowired
    private SubscriptionPlanRepository subscriptionPlanRepository;
    @Override
    public SubscriptionPlan createSubscriptionPlan(SubscriptionPlan subscriptionPlan)
    {
        return subscriptionPlanRepository.save(subscriptionPlan);
    }

    @Override
    public List<SubscriptionPlan> getAllSubscriptionPlan() {
        return subscriptionPlanRepository.findAll();
    }
}
