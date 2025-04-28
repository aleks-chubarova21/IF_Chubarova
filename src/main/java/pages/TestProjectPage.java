package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.visible;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestProjectPage {
    private final SelenideElement tasksCounter = $x("//div[@class='showing']//span");
    private final SelenideElement testProjectLink = $x("//h1//a[@title='Test']");

    public void verifyProjectOpened() {
        testProjectLink.shouldBe(visible);
    }

    public int getTasksCount() {
        String counterText = tasksCounter.shouldBe(visible).getText();
        return Integer.parseInt(counterText.split("из")[1].trim());
    }

    public void verifyTasksCountIncreased(int initialCount) {
        String expectedText = "1 из " + (initialCount + 1);
        assertEquals(expectedText, tasksCounter.getText());
    }
}