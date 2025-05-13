package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import util.TestProperties;
import static io.restassured.http.ContentType.JSON;

public class RickAndMortyApi {
    public static RequestSpecification getBaseSpec(){
        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(TestProperties.getProperty("urlRickAndMorty"))
                .build();
    }

}

