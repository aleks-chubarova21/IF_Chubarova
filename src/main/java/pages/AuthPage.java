package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class AuthPage {
    private final SelenideElement usernameInput = $x("//input[@id='login-form-username']");
    private final SelenideElement passwordInput = $x("//input[@id='login-form-password']");
    private final SelenideElement loginButton = $x("//input[@id='login-form-submit']");
    private final SelenideElement userProfileIcon = $x("//a[@id='header-details-user-fullname']");

    public void login(String username, String password) {
        usernameInput.shouldBe(visible).setValue(username);
        passwordInput.shouldBe(visible).setValue(password);
        loginButton.shouldBe(visible).click();
        userProfileIcon.shouldBe(visible);
    }
    public boolean isUserLoggedIn() {
        return userProfileIcon.isDisplayed();
    }
}
