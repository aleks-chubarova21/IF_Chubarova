package test;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.ReqresSteps;
import config.Props;
import org.aeonbits.owner.ConfigFactory;

public class ReqresTest {
    private static final Props props = ConfigFactory.create(Props.class);

    @Test
    @DisplayName("Создание пользователя и проверка ответа")
    public void testCreateUser() {
        ValidatableResponse response = ReqresSteps.createUser();
        Assertions.assertNotNull(response, "Ответ не должен быть null");

        String name = response.extract().path("name");
        String job = response.extract().path("job");
        int statusCode = response.extract().statusCode();

        Assertions.assertEquals(props.newName(), name, "Имя пользователя должно совпадать");
        Assertions.assertEquals(props.newJob(), job, "Работа пользователя должна совпадать");
        Assertions.assertEquals(Integer.parseInt(props.statusCodeReqRes()), statusCode, "Статус код должен совпадать");
    }
}