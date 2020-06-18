package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.MedicalProfile;
import com.pe.edu.upc.petcare.model.Pet;
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
public class MedicalProfileStepdefs {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @When("^El veterinario registra un historal medico con name (.*), weight (.*), height (.*), length (.*), eyes (.*), breed (.*), gender (.*), color (.*), description (.*), photo (.*), age (.*)$")
    public void elVeterinarioRegistraUnHistoralMedicoConNameNameWeightWeightHeightHeightLengthLengthEyesEyesBreedBreedGenderGenderColorColorDescriptionDescriptionPhotoPhotoAgeAge(
            String name, String weight, String height, String length, String eyes, String breed, String gender, String color, String description, String photo, Integer age) {

        String url = Url + ":" + port + "/api/business/1/providers/1/people/1/pets/1/pet-profiles";
        MedicalProfile newmedicalProfile = new MedicalProfile();
        newmedicalProfile.setName(name);
        newmedicalProfile.setWeight(weight);
        newmedicalProfile.setHeight(height);
        newmedicalProfile.setLength(length);
        newmedicalProfile.setEyes(eyes);
        newmedicalProfile.setBreed(breed);
        newmedicalProfile.setGender(gender);
        newmedicalProfile.setColor(color);
        newmedicalProfile.setDescription(description);
        newmedicalProfile.setPhoto(photo);
        newmedicalProfile.setAge(age);
        MedicalProfile medicalProfile = restTemplate.postForObject(url, newmedicalProfile, MedicalProfile.class);
        postId = medicalProfile.getId();
        System.out.println(postId);
        assertEquals(medicalProfile.getName(),"kled");
    }

    @Then("Verificar que se ha registrado un historial medico")
    public void verificarQueSeHaRegistradoUnHistorialMedico() {
        String url = Url + ":" + port + "/api/business/1/providers/1/people/1/pets/1/pet-profiles/"+ postId;
        System.out.println(postId);
        MedicalProfile medicalProfileBD = restTemplate.getForObject(url, MedicalProfile.class);
        assertEquals(medicalProfileBD.getName(),"kled");
    }
}
