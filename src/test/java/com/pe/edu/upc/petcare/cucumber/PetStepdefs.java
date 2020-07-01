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

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PetStepdefs {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @Given("El usuario se haya registrado")
    public void elUsuarioSeHayaRegistrado() {
        String url = Url + ":" + port + "/api/people/1";
        PersonProfile personProfileDB = restTemplate.getForObject(url, PersonProfile.class);
        assertEquals(personProfileDB.getName(),"miguel");
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
        // assertEquals(pet.getName(),"nuevamascota2");
    }

    @Then("Verificar que se ha registrado la nueva mascota")
    public void verificarQueSeHaRegistradoLaNuevaMascota() {
        String url = Url + ":" + port + "/api/people/1/pets/"+ postId;
        System.out.println(postId);
        Pet petBD = restTemplate.getForObject(url, Pet.class);
        //  assertEquals(petBD.getName(),"nuevamascota2");
    }





}



