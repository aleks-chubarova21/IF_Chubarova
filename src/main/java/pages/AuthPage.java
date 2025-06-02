package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

public class AuthPage {
    private final SelenideElement usernameInput = $x("//input[@id='login-form-username']");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']");
    private final SelenideElement loginButton = $x("//input[@id='login-form-submit']");
    private final SelenideElement userProfileIcon = $x("//a[@id='header-details-user-fullname']");
    private final SelenideElement errorMessage = $x("//div[contains(@class, 'aui-message-error')]");

    @Step("Открытие страницы авторизации: {url}")
    public void open(String url) {
        Selenide.open(url);
    }

    @Step("Авторизация пользователя: {username}")
    public void login(String username, String password) {
        usernameInput.shouldBe(visible, interactable).setValue(username);
        passwordInput.shouldBe(visible, interactable).setValue(password);
        loginButton.shouldBe(visible, interactable).click();
        userProfileIcon.shouldBe(visible);
    }

    @Step("Проверка авторизации пользователя")
    public boolean isUserLoggedIn() {
        return userProfileIcon.isDisplayed();
    }
}
