package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import config.Props;
import org.aeonbits.owner.ConfigFactory;

import static io.restassured.http.ContentType.JSON;

public class ReqresApi {
    private static final Props props = ConfigFactory.create(Props.class);

    protected static RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .log(LogDetail.ALL)
                .setContentType(JSON)
                .setBaseUri(props.baseUrlReqRes())
                .addHeader(props.apiKeyHeaderName(), props.apiKeyReqRes())
                .build();
    }
}
