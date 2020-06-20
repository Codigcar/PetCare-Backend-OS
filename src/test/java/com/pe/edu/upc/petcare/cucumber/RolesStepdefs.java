package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.Rol;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
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
public class RolesStepdefs {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;


    @Given("^Se crea un rol con name (.*)$")
    public void seCreaUnRolConNameName(String name) {
        String url = Url + ":" + port + "/api/admin/roles";
        Rol newRol = new Rol();
        newRol.setName(name);
        Rol rol = restTemplate.postForObject(url, newRol, Rol.class);
        postId = rol.getId();
      //  assertEquals(rol.getName(),"veterinaria");
    }

    @Then("Verificar si se ha creado un rol")
    public void verificarSiSeHaCreadoUnRol() {
        String url = Url + ":" + port + "/api/admin/roles/"+ postId;
        System.out.println(postId);
        Rol rolBD = restTemplate.getForObject(url, Rol.class);
    //    assertEquals(rolBD.getName(),"veterinaria");
    }
}
