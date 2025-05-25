package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.aeonbits.owner.ConfigFactory;
import config.Props;

import static io.restassured.http.ContentType.JSON;

public class RickAndMortyApi {
    private static final Props props = ConfigFactory.create(Props.class);

    public static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setContentType(JSON)
                .setBaseUri(props.urlRickAndMorty())
                .build();
    }
}
