package com.pe.edu.upc.petcare;

import com.pe.edu.upc.petcare.model.ProductType;
import com.pe.edu.upc.petcare.model.Rol;
import com.pe.edu.upc.petcare.model.SubscriptionPlan;
import com.pe.edu.upc.petcare.repository.ProductTypeRepository;
import com.pe.edu.upc.petcare.repository.RolRepository;
import com.pe.edu.upc.petcare.repository.SubscriptionPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private RolRepository repository;
    @Autowired
    private SubscriptionPlanRepository planRepository;
    @Autowired
    private ProductTypeRepository productTypeRepository;

    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();
        planRepository.deleteAll();
        productTypeRepository.deleteAll();

        repository.save(new Rol((long) 1,"Users"));
        repository.save(new Rol((long) 2,"Veterinary"));

        planRepository.save(new SubscriptionPlan((long) 1, "Free", "Free plan", 1, 0.0));
        planRepository.save(new SubscriptionPlan((long) 2,"Basic","Basic plan",1,19.90));
        planRepository.save(new SubscriptionPlan((long) 3,"Premium","Premium plan",1,29.90));

        productTypeRepository.save(new ProductType((long) 1, "Servicios Generales"));
        productTypeRepository.save(new ProductType((long) 2, "Especialidades"));
        productTypeRepository.save(new ProductType((long) 3, "Examenes complementarios"));

    }
}