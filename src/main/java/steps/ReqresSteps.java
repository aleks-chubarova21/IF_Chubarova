package steps;

import api.ReqresApi;
import io.restassured.response.ValidatableResponse;
import util.TestProperties;
import static io.restassured.RestAssured.given;

public class ReqresSteps {
    public static ValidatableResponse createUser() {
        return given()
                .spec(ReqresApi.getBaseSpec())
                .body("{\"name\": \"" + TestProperties.getProperty("newName") +
                      "\", \"job\": \"" + TestProperties.getProperty("newJob") + "\"}")
                .when()
                .post(TestProperties.getProperty("postUrlReqRes"))
                .then()
                .log().all();
    }
}