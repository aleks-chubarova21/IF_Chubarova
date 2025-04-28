package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.sleep;
import static com.codeborne.selenide.Condition.*;

public class TaskPage {
    private final SelenideElement statusField = $x("//span[@id='status-val']");
    private final SelenideElement currentStatus = $x("//span[@id='status-val']/span");
    private final SelenideElement businessProcessButton = $x("//span[text()='Бизнес-процесс']");
    private final SelenideElement inProgressButton = $x("//span[text()='В работе']/ancestor::a");
    private final SelenideElement doneButton = $x("//span[text()='Выполнено']/ancestor::a");
    
    public void openTask(String taskId) {
        open("/browse/" + taskId);
        statusField.shouldBe(visible);
    }
    
    public void clickInProgressButton() {
        inProgressButton.shouldBe(visible, enabled).click();
        sleep(2000);
    }
    
    public void clickBusinessProcessAndDone() {

        businessProcessButton.shouldBe(visible, enabled).click();
        sleep(1000);

        doneButton.shouldBe(visible, enabled).click();
        sleep(2000);
    }
    public boolean verifyCurrentStatus(String expectedStatus) {
        sleep(1000);
        return currentStatus.shouldBe(visible).getText().equals(expectedStatus);
    }
}
