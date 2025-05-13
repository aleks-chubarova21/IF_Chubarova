package api;

import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;

public class ReqresApi {
    public static RequestSpecification getBaseSpec() {
        return given()
                .baseUri(util.TestProperties.getProperty("baseUrlReqRes"))
                .contentType("application/json")
                .accept("application/json")
                .header("x-api-key", "reqres-free-v1");
    }
}
