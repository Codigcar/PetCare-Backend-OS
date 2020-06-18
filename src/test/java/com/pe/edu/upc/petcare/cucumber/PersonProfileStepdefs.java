package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Pet;
import io.cucumber.java.an.Pero;
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
public class PersonProfileStepdefs {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @Given("El usuario ingresa a la aplicacion web")
    public void elUsuarioIngresaALaAplicacionWeb() {

    }


    @When("^El usuario se registra con name (.*), password (.*),lastName (.*), document (.*), email (.*), phone(.*), age(.*)$")
    public void elUsuarioSeRegistraConPersonprofile_idIdNameNamePasswordPasswordLastNameLastNameDocumentDocumentEmailEmailPhonePhoneAgeAge(
            String name, String password, String lastName, Long document, String email, Long phone, Integer age) {

        String url = Url + ":" + port + "/api/people";
        PersonProfile newpersonProfile = new PersonProfile();
        newpersonProfile.setName(name);
        newpersonProfile.setPassword(password);
        newpersonProfile.setLastName(lastName);
        newpersonProfile.setDocument(document);
        newpersonProfile.setEmail(email);
        newpersonProfile.setPhone(phone);
        newpersonProfile.setAge(age);

        PersonProfile personProfile = restTemplate.postForObject(url,newpersonProfile, PersonProfile.class);
        postId = personProfile.getId();
        System.out.println(postId);
        assertEquals(personProfile.getName(),"carlos");
    }

    @Then("Verificar que se ha registrado el usuario")
    public void verificarQueSeHaRegistradoElUsuario() {
        String url = Url + ":" + port + "/api/people/"+ postId;
        System.out.println(postId);
        PersonProfile personProfileBd = restTemplate.getForObject(url, PersonProfile.class);
        assertEquals(personProfileBd.getName(),"carlos");
    }


}
