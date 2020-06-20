package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.model.Provider;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;

public class ProviderStepdefs {
    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @When("^El usuario registra los datos de la veterinaria con nombre (.*), region (.*), direccion (.*), campo (.*), email (.*), descripcion (.*)$")
    public void el_usuario_registra_los_datos_de_la_veterinaria_con_nombre_cachorros_region_region1_direccion_address1_campo_field1_email_email1_gmail_com_descripcion_description1(
            String name, String region, String address, String field, String email, String description
    ) {
        String url = Url + ":" + port + "/api/business/1/providers/1";
        Provider newprovider = new Provider();
        newprovider.setBusinessName(name);
        newprovider.setRegion(region);
        newprovider.setAddress(address);
        newprovider.setField(field);
        newprovider.setEmail(email);
        newprovider.setDescription(description);
        restTemplate.put(url,newprovider);

        assertEquals(1,1);
    }

    @Then("Verificar que los datos de la veterinaria se hayan registrado")
    public void verificarQueLosDatosDeLaVeterinariaSeHayanRegistrado() {
        String url = Url + ":" + port + "/api/business/1/providers/1";
        Provider providerBD = restTemplate.getForObject(url, Provider.class);
        assertEquals(providerBD.getBusinessName(),"cachorros");
    }
}
