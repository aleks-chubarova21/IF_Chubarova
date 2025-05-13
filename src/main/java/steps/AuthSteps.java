package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.AuthPage;
import util.TestProperties;

public class AuthSteps {
    private final AuthPage authPage = new AuthPage();

    @Дано("Открыта страница авторизации")
    public void openAuthPage() {
        authPage.open(TestProperties.getProperty("url"));
    }
    @Когда("Пользователь вводит валидные данные")
    public void enterValidCredentials() {
        authPage.login(
                TestProperties.getProperty("login"),
                TestProperties.getProperty("password")
        );
    }

    @Тогда("Авторизация проходит успешно")
    public void verifySuccessfulLogin() {
        Assertions.assertTrue(
                authPage.isUserLoggedIn(),
                "Пользователь не авторизован"
        );
    }
}
