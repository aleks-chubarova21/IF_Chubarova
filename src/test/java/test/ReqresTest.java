package test;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.ReqresSteps;
import util.TestProperties;

import static org.hamcrest.Matchers.*;

public class ReqresTest {

    @Test
    @DisplayName("Создание пользователя и проверка ответа")
    public void testCreateUser() {
        ValidatableResponse response = ReqresSteps.createUser();

        System.out.println("\nСоздание пользователя:");
        System.out.println("Имя: " + TestProperties.getProperty("newName"));
        System.out.println("Работа: " + TestProperties.getProperty("newJob"));
        System.out.println("URL запроса: " + TestProperties.getProperty("baseUrlReqRes") + 
                          TestProperties.getProperty("postUrlReqRes"));

        response
                .statusCode(Integer.parseInt(TestProperties.getProperty("statusCodeReqRes")))
                .body("name", equalTo(TestProperties.getProperty("newName")))
                .body("job", equalTo(TestProperties.getProperty("newJob")));

    }
}