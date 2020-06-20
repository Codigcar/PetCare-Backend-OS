package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.BusinessProfile;
import com.pe.edu.upc.petcare.model.PersonProfile;
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
/*@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)*/
public class BusinessProfileStepdefs {
    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @Given("El business ingresa a la aplicacion web")
    public void elBusinessIngresaALaAplicacionWeb() {
    }
    @When("^EL business perteneciente a una veterinaria crea su perfil con name (.*), password (.*), lastname (.*), document (.*), email (.*), phone (.*) , age (.*), owner (.*)$")
    public void el_business_perteneciente_a_una_veterinaria_crea_su_perfil_con_name_business1_password_password_lastname_lastname1_document_email_business331_gmail_com_phone_age_owner_true(
        String name, String password, String asd, Long document, String email, Long phone, int age, Boolean owner) {
            String url = Url + ":" + port + "/api/business";
            BusinessProfile newbusinessProfile = new BusinessProfile();
            newbusinessProfile.setName(name);
            newbusinessProfile.setPassword(password);
            newbusinessProfile.setLastName(asd);
            newbusinessProfile.setDocument(document);
            newbusinessProfile.setEmail(email);
            newbusinessProfile.setPhone(phone);
            newbusinessProfile.setAge(age);
            newbusinessProfile.setOwner(owner);

            BusinessProfile businessProfile = restTemplate.postForObject(url, newbusinessProfile, BusinessProfile.class);
            postId = businessProfile.getId();
            //assertEquals(businessProfile.getName(),"business1");
    }

    @Then("Verficar si se ha creado un perfil del business")
    public void verficarSiSeHaCreadoUnPerfilDelBusiness() {
        String url = Url + ":" + port + "/api/business/"+ postId;
        BusinessProfile businessProfileBD = restTemplate.getForObject(url, BusinessProfile.class);
        //assertEquals(businessProfileBD.getName(),"business1");
    }

}
