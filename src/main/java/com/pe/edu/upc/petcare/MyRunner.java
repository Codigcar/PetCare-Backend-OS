package com.pe.edu.upc.petcare;

import com.pe.edu.upc.petcare.model.Rol;
import com.pe.edu.upc.petcare.model.SubscriptionPlan;
import com.pe.edu.upc.petcare.repository.RolRepository;
import com.pe.edu.upc.petcare.repository.SubscriptionPlanRepository;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class MyRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(MyRunner.class);

    @Autowired
    private RolRepository repository;
    @Autowired
    private SubscriptionPlanRepository planRepository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        repository.save(new Rol("Users"));
        repository.save(new Rol("Veterinary"));

        planRepository.save(new SubscriptionPlan("Free","Free plan",1,0.0));
        planRepository.save(new SubscriptionPlan("Basic","Basic plan",1,19.90));
        planRepository.save(new SubscriptionPlan("Premium","Premium plan",1,29.90));
    }
}