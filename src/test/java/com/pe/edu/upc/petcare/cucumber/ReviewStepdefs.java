package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.Review;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;

public class ReviewStepdefs {
    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @Given("^El usuario registra su review con commentary (.*), qualification (.*)$")
    public void elUsuarioRegistraSuReviewConCommentaryCommentaryQualificationQualification(String commentary, int qualification) {
        String url = Url + ":" + port + "/api/people/1/providers/1/reviews";
        Review newreview = new Review();
        newreview.setCommentary(commentary);
        newreview.setQualification(qualification);

        Review review = restTemplate.postForObject(url, newreview, Review.class);
        postId = review.getId();
        System.out.println(postId);
        assertEquals(review.getCommentary(),"excelente");
    }

    @And("bb <name>")
    public void bbName() {

    }
}
