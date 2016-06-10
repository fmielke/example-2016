import com.jayway.restassured.RestAssured;
import de.htwberlin.mae.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by fmielke on 10.06.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class testRootAssured {

    @Value("${local.server.port}")
    private int serverPort;

    @Before
    public void setUp() {
        RestAssured.port = serverPort;
    }

    @Test
    public void rootOK() {
        when()
                .get("/api")
                .then()
                .log().all()
                .statusCode(200);
    }

    @Test
    public void nutzerPostCreated() {
        given()
                .contentType("application/json")
                .body("{\"name\":\"john\"}")
        .when()
                .post("/api/nutzer")
        .then()
                .log().all()
                .statusCode(201)
                .header("location", containsString("/nutzer/"))
                .body("name", equalTo("john"));
    }
}
