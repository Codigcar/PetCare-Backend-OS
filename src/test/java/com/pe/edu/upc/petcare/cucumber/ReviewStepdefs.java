package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.model.Review;
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
public class ReviewStepdefs {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @When("^El usuario realiza un comentario con commentary (.*), qualifiaction (.*)$")
    public void elUsuarioRealizaUnComentarioConCommentaryCommentaryQualifiactionQualification(
            String commentary, int qualifiaction) {
        String url = Url + ":" + port + "/api/people/1/providers/1/reviews";
        Review newreview = new Review();
        newreview.setCommentary(commentary);
        newreview.setQualification(qualifiaction);

        Review review = restTemplate.postForObject(url, newreview, Review.class);
        postId = review.getId();
        System.out.println(postId);
        assertEquals(review.getCommentary(),"Buen servicio");
    }

    @Then("Verificar que se ha realizado un comentario")
    public void verificarQueSeHaRealizadoUnComentario() {
        String url = Url + ":" + port + "/api/people/1/providers/1/reviews/"+ postId;
        System.out.println(postId);
        Review reviewBD = restTemplate.getForObject(url, Review.class);
        assertEquals(reviewBD.getCommentary(),"Buen servicio");
    }
}
