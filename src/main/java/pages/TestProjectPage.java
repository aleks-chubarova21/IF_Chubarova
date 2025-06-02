package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;

public class TestProjectPage {
    private final SelenideElement projectHeader = $x("//h1//a[@title='Test']");
    private final SelenideElement issuesCount = $x("//div[@class='showing']//span");

    @Step("Проверка открытия проекта")
    public void verifyProjectOpened() {
        projectHeader.shouldBe(visible);
    }

    @Step("Получение количества задач в проекте")
    public int getTasksCount() {
        String counterText = issuesCount.shouldBe(visible).getText();
        return Integer.parseInt(counterText.split("из")[1].trim());
    }
}