package steps;

import io.restassured.response.ValidatableResponse;
import api.ReqresApi;
import config.Props;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ReqresSteps extends ReqresApi {
    private static final Props props = ConfigFactory.create(Props.class);

    public static ValidatableResponse createUser() {
        return given()
                .spec(getBaseSpec())
                .body("{\n" + "    \"name\": \"" + props.newName() + "\",\n" + "    \"job\": \"" + props.newJob() + "\"\n" + "}")
                .when()
                .post(props.postUrlReqRes())
                .then()
                .log().all()
                .assertThat()
                .statusCode(Integer.parseInt(props.statusCodeReqRes()))
                .body("name", equalTo(props.newName()))
                .body("job", equalTo(props.newJob()));
    }
}