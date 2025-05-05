package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Condition.*;
import java.time.Duration;

public class TaskPage {
    private final SelenideElement statusField = $x("//span[@id='status-val']");
    private final SelenideElement currentStatus = $x("//span[@id='status-val']/span");
    private final SelenideElement businessProcessButton = $x("//span[text()='Бизнес-процесс']");
    private final SelenideElement inProgressButton = $x("//span[text()='В работе']/ancestor::a");
    private final SelenideElement doneButton = $x("//span[text()='Выполнено']/ancestor::a");


    public void clickInProgressButton() {
        inProgressButton.shouldBe(interactable, Duration.ofSeconds(10)).click();
        statusField.shouldHave(text("В РАБОТЕ"), Duration.ofSeconds(10));
    }

    public void clickBusinessProcessAndDone() {
        businessProcessButton.shouldBe(interactable, Duration.ofSeconds(10)).click();
        doneButton.shouldBe(interactable, Duration.ofSeconds(10)).click();
        statusField.shouldHave(text("ГОТОВО"), Duration.ofSeconds(15));
    }

    public boolean verifyCurrentStatus(String expectedStatus) {
        return currentStatus.shouldBe(visible, Duration.ofSeconds(5)).getText().equals(expectedStatus);
    }
}
