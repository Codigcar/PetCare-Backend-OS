import com.pe.edu.upc.petcare.model.PersonProfile;
import com.pe.edu.upc.petcare.model.Pet;
import com.pe.edu.upc.petcare.model.Rol;
import com.pe.edu.upc.petcare.model.SubscriptionPlan;
import cucumber.api.java.en.Then;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class StepDefinitions {

    @LocalServerPort
    private int port;
    private RestTemplate restTemplate = new RestTemplate();
    private String postUrl = "http://localhost";
    private long postId = 0;


    @Given("^I sending post to be created with name (.*)$")
    public void i_sending_post_to_be_created_with_rol_id_name_customer(String name) {
        String url = postUrl + ":" + port + "/api/admin/roles";
        Rol newRol = new Rol();
        newRol.setName(name);
        Rol rol = restTemplate.postForObject(url, newRol, Rol.class);
        postId = rol.getId();
        log.info(rol);
        assertEquals(rol.getName(),"bb");
    }


  /*  @Given("^I sending SubscriptionPlan to be created with subscriptionPlan_id (.*),name (.*), description (.*),duration (.*), price (.*)$")
    public void i_sending_SubscriptionPlan_to_be_created_with_subscriptionPlan_id_name_Basico_description_plan_Basico_duration_price(String id,String name, String description, String duration, String price) {
        String url = postUrl + ":" + port + "/api/admin/subscription-plan";
        SubscriptionPlan newSubscription = new SubscriptionPlan();
        newSubscription.setId((long) Integer.parseInt(id));
        newSubscription.setName(name);
        newSubscription.setDuration(Integer.parseInt(duration) );
        newSubscription.setPrice(Double.parseDouble(price));
        SubscriptionPlan subscriptionPlan = restTemplate.postForObject(url, newSubscription, SubscriptionPlan.class);
        log.info(subscriptionPlan);
        assertEquals(subscriptionPlan.getName(),"Basico");
    }
*/




}
