package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class AuthPage {
    private final SelenideElement usernameInput = $x("//input[@id='login-form-username']");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']");
    private final SelenideElement loginButton = $x("//input[@id='login-form-submit']");
    private final SelenideElement userProfileIcon = $x("//a[@id='header-details-user-fullname']");

    @Step("Открытие страницы авторизации по URL: {url}")
    public void open (String url) {
        Selenide.open(url);
    }

    @Step("Выполнение авторизации пользователя: {username}")
    public void login(String username, String password) {
        inputUsername(username);
        inputPassword(password);
        submitLogin();
        verifyLoginSuccess();
    }

    @Step("Ввод логина")
    private void inputUsername(String username) {
        usernameInput
                .shouldBe(visible)
                .setValue(username);
    }

    @Step("Ввод пароля")
    private void inputPassword(String password) {
        passwordInput
                .shouldBe(visible)
                .setValue(password)
                .sensitive();
    }

    @Step("Нажатие кнопки входа")
    private void submitLogin() {
        loginButton
                .shouldBe(visible)
                .click();
    }

    @Step("Проверка успешной авторизации")
    public boolean isUserLoggedIn() {
        return userProfileIcon
                .shouldBe(visible)
                .isDisplayed();
    }

    @Step("Подтверждение успешного входа")
    private void verifyLoginSuccess() {
        userProfileIcon.shouldBe(visible);
    }
}
