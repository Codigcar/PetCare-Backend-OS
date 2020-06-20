package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.model.Review;
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
public class PetStepdefs {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;


    @Given("El usuario registra sus datos")
    public void elUsuarioRegistraSusDatos() {

    }

  @When("^El usuario registra una mascota con name (.*), age (.*), breed (.*), photo (.*), gender (.*)$")
    public void elUsuarioRegistraUnaMascotaConNameNameAgeAgeBreedBreedPhotoPhotoGenderGender(String name, Integer age, String breed, String photo, String gender ) {
        String url = Url + ":" + port + "/api/people/1/pets";
        Pet newpet = new Pet();
        newpet.setName(name);
        newpet.setAge(age);
        newpet.setBreed(breed);
        newpet.setPhoto(photo);
        newpet.setGender(gender);
        Pet pet = restTemplate.postForObject(url, newpet, Pet.class);
        postId = pet.getId();
        System.out.println(postId);
        assertEquals(pet.getName(),"nuevamascota");
    }

    @Then("Verificar que se ha registrado la nueva mascota")
    public void verificarQueSeHaRegistradoLaNuevaMascota() {
        String url = Url + ":" + port + "/api/people/1/pets/"+ postId;
        System.out.println(postId);
        Pet petBD = restTemplate.getForObject(url, Pet.class);
        assertEquals(petBD.getName(),"nuevamascota");
    }
    @When("^El usuario registraa una mascota con name (.*), password (.*), lastname (.*), document (.*), email (.*), phone (.*), age (.*)$")
    public void el_usuario_registraa_una_mascota_con_name_name11_password_password11_lastname_lastname2_document_email_carlos2323as_gmail_com_phone_age(String name, String password, String lastname, Long document, String email, Long phone, Integer age) {
        String url = Url + ":" + port + "/api/people";
        PersonProfile newpet = new PersonProfile();
        newpet.setName(name);
        newpet.setPassword(password);
        newpet.setLastName(lastname);
        newpet.setDocument(document);
        newpet.setEmail(email);
        newpet.setPhone(phone);
        newpet.setAge(age);
        PersonProfile pet = restTemplate.postForObject(url, newpet, PersonProfile.class);
        postId = pet.getId();
        System.out.println(postId);

        assertEquals(1,1);
    }

    @When("bb name name11, password password11, asd lastname2, document {int}, email carlos2323as@gmail.com, phone {int} , age {int}")
    public void bb_name_name11_password_password11_asd_lastname2_document_email_carlos2323as_gmail_com_phone_age(Integer int1, Integer int2, Integer int3) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


}
