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

    @Autowired
    private RolRepository repository;
    @Autowired
    private SubscriptionPlanRepository planRepository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        planRepository.deleteAll();

        repository.save(new Rol((long) 1,"Users"));
        repository.save(new Rol((long) 2,"Veterinary"));

        planRepository.save(new SubscriptionPlan((long) 1, "Free", "Free plan", 1, 0.0));
        planRepository.save(new SubscriptionPlan((long) 2,"Basic","Basic plan",1,19.90));
        planRepository.save(new SubscriptionPlan((long) 3,"Premium","Premium plan",1,29.90));
    }
}