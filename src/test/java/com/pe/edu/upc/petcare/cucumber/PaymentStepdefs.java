package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.PersonProfile;
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
public class PaymentStepdefs {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @Given("El veterinario se haya registrado")
    public void elVeterinarioIngresaALaAplicacionWeb() {
        String url = Url + ":" + port + "/api/business/1/providers/1";
        Provider providerBD = restTemplate.getForObject(url, Provider.class);
        assertEquals(providerBD.getEmail(),"business331@gmail.com");
    }


    @When("^El veterinario registra su tarjeta con card number(.*), card name (.*), cvv number (.*), expiry date (.*)$")
    public void elVeterinarioRegistraSuTarjetaConCardNumberCard_numberCardNameCard_nameCvvNumberCvv_numberExpiryDateExpiry_date(
            
    ) {
    }

    @Then("Verificar si se ha registrado la tarjeta")
    public void verificarSiSeHaRegistradoLaTarjeta() {
    }
}
