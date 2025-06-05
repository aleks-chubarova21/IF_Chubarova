package cucumber;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Step;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import steps.ReqresSteps;
import org.junit.jupiter.api.Assertions;
import io.restassured.response.ValidatableResponse;
import config.Props;
import org.aeonbits.owner.ConfigFactory;

@Story("Reqres API Tests")
public class ReqresCucumberStep {
    private static final Props props = ConfigFactory.create(Props.class);
    private ValidatableResponse apiResponse;

    @Дано("подготовлены данные для создания пользователя")
    @Step("Подготовка данных для создания пользователя")
    @Description("Подготовка тестовых данных из properties файла для создания нового пользователя")
    public void prepareUserData() {
        apiResponse = ReqresSteps.createUser();
        Assertions.assertNotNull(apiResponse);
    }

    @Когда("отправляется запрос на создание пользователя")
    @Step("Отправка запроса на создание пользователя")
    @Description("Отправка POST запроса на создание нового пользователя с указанными данными")
    public void createUser() {
        apiResponse = ReqresSteps.createUser();
        Assertions.assertNotNull(apiResponse);
    }

    @Тогда("пользователь успешно создан")
    @Step("Проверка успешного создания пользователя")
    @Description("Проверка статус кода и данных в ответе после создания пользователя")
    public void verifyUserCreated() {
        Assertions.assertEquals(Integer.parseInt(props.statusCodeReqRes()),
                apiResponse.extract().statusCode(),
                "Статус код должен быть 201");
    }

    @Тогда("в ответе указаны корректные данные пользователя")
    @Step("Проверка данных пользователя в ответе")
    @Description("Проверка соответствия данных созданного пользователя отправленным данным")
    public void verifyUserData() {
        String actualName = apiResponse.extract().path("name");
        String actualJob = apiResponse.extract().path("job");

        Assertions.assertEquals(props.newName(), actualName, "Имя пользователя должно совпадать");
        Assertions.assertEquals(props.newJob(), actualJob, "Должность пользователя должна совпадать");
    }
}