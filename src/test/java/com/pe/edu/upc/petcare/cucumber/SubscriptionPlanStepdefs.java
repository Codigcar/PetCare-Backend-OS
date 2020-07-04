package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.SubscriptionPlan;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;
/*
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)*/
public class SubscriptionPlanStepdefs {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @Given("^Se crea una suscripcion con name (.*), description (.*), duration (.*), price (.*)")
    public void seCreaUnaSuscripcionConNameNameDescriptionDescriptionDurationDurationPricePrice(
            String name, String description, int duration, Double price) {
        String url = Url + ":" + port + "/api/admin/subscription-plan";
        SubscriptionPlan newsubscriptionPlan = new SubscriptionPlan();
        newsubscriptionPlan.setName(name);
        newsubscriptionPlan.setDescription(description);
        newsubscriptionPlan.setDuration(duration);
        newsubscriptionPlan.setPrice(price);

        SubscriptionPlan subscriptionPlan = restTemplate.postForObject(url, newsubscriptionPlan, SubscriptionPlan.class);
        postId = subscriptionPlan.getId();
        System.out.println(postId);
   //     assertEquals(subscriptionPlan.getName(),"Basico");
    }

    @Then("Verificar si se ha creado una suscripcion")
    public void verificarSiSeHaCreadoUnaSuscripcion() {
        String url = Url + ":" + port + "/api/admin/subscription-plan/"+ postId;
        System.out.println(postId);
        SubscriptionPlan subscriptionPlanBD = restTemplate.getForObject(url, SubscriptionPlan.class);
   //     assertEquals(subscriptionPlanBD.getName(),"Basico");
    }
}
