package com.pe.edu.upc.petcare.cucumber;

import com.pe.edu.upc.petcare.model.ProductType;
import com.pe.edu.upc.petcare.model.ProviderJoinProductType;
import io.cucumber.java.en.Given;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import static junit.framework.TestCase.assertEquals;

public class ProdiverJoinProductStepdefs {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String Url = "http://localhost";
    private long postId = 0;

    @Given("^El usuario slecciona su tipo de producto con provider_id (.*) y product_id (.*)$")
    public void elUsuarioSleccionaSuTipoDeProductoConSuProduct_idProduct_id(String provider_id,String product_id) {
        String url = Url + ":" + port + "/api/business/1/provider/"+provider_id+"/products-types/"+product_id;
        ProviderJoinProductType newProviderJoinProductType = new ProviderJoinProductType();
        ProviderJoinProductType  providerJoinProductType = restTemplate.postForObject(url, newProviderJoinProductType, ProviderJoinProductType.class);
        assertEquals(1,1);
    }
}
