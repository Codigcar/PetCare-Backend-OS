package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.model.Provider;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class VeterinaryStepdefs {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @Given("El veterinario ingresa a la aplicacion web")
    public void elVeterinarioIngresaALaAplicacionWeb() {
    }

    @When("^El veterinario registra una veterinaria con address (.*), business name (.*), description (.*), email (.*), field (.*), region (.*)$")
    public void elVeterinarioRegistraUnaVeterinariaConAddressAddressBusinessNameBusiness_nameDescriptionDescriptionEmailEmailFieldFieldRegionRegion(
            String address, String business_name, String description, String email, String field, String region) {

        String url = Url + ":" + port + "/api/business/1/providers";
        Provider newProvider = new Provider();
        newProvider.setAddress(address);
        newProvider.setBusinessName(business_name);
        newProvider.setDescription(description);
        newProvider.setEmail(email);
        newProvider.setField(field);
        newProvider.setRegion(region);
        Provider provider = restTemplate.postForObject(url, newProvider, Provider.class);
        postId = provider.getId();
        System.out.println(postId);
        assertEquals(provider.getBusinessName(), "PetHouse");
    }

    @Then("Verificar que se ha registrado la veterinaria")
    public void verificarQueSeHaRegistradoLaVeterinaria() {
        String url = Url + ":" + port + "/api/business/1/providers/" + postId;
        System.out.println(postId);
        Provider providerBD = restTemplate.getForObject(url, Provider.class);
        assertEquals(providerBD.getBusinessName(), "PetHouse");
    }


}
