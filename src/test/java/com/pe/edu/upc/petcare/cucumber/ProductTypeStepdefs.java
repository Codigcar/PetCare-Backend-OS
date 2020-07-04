package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.model.ProductType;
import io.cucumber.java.en.Given;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;

public class ProductTypeStepdefs {
    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @Given("^El usuario registra su tipo de producto con su nombre (.*)")
    public void elUsuarioRegistraSuTipoDeProductoConSuNombreName(String name) {

        /*String url = Url + ":" + port + "/api/admin/product-types";
        ProductType newproductType = new ProductType();
        newproductType.setName(name);
        ProductType  productType = restTemplate.postForObject(url, newproductType, ProductType.class);
        postId = productType.getId();
        System.out.println(postId);
        assertEquals(productType.getName(),"baño");*/

    }
}
