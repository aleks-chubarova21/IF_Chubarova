package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;
import java.time.Duration;

public class TestProjectPage {
    private final SelenideElement projectHeader = $x("//h1//a[@title='Test']")
            .as("Заголовок проекта");
    private final SelenideElement issuesCount = $x("//div[@class='showing']//span")
            .as("Счетчик задач");

    @Step("Проверка открытия проекта")
    public void verifyProjectOpened() {
        projectHeader.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    @Step("Получение количества задач в проекте")
    public int getTasksCount() {
        String counterText = issuesCount.shouldBe(Condition.visible, Duration.ofSeconds(10)).getText();
        return Integer.parseInt(counterText.split("из")[1].trim());
    }
}