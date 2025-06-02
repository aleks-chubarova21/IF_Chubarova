package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.*;

public class TaskPage {
    private final SelenideElement statusField = $x("//span[@id='status-val']");
    private final SelenideElement currentStatus = $x("//span[@id='status-val']/span");
    private final SelenideElement businessProcessButton = $x("//span[text()='Бизнес-процесс']");
    private final SelenideElement inProgressButton = $x("//span[text()='В работе']/ancestor::a");
    private final SelenideElement doneButton = $x("//span[text()='Выполнено']/ancestor::a");

    @Step("Перевод задачи в статус 'В работе'")
    public void clickInProgressButton() {
        inProgressButton.shouldBe(interactable).click();
        statusField.shouldHave(text("В РАБОТЕ"));
    }

    @Step("Перевод задачи в статус 'Готово' через бизнес-процесс")
    public void clickBusinessProcessAndDone() {
        businessProcessButton.shouldBe(interactable).click();
        doneButton.shouldBe(interactable).click();
        statusField.shouldHave(text("ГОТОВО"));
    }

    @Step("Проверка текущего статуса задачи")
    public boolean verifyCurrentStatus(String expectedStatus) {
        return currentStatus.shouldBe(visible).getText().equals(expectedStatus);
    }
}