package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.$x;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestTaskPage {
    private final SelenideElement taskSummary = $x("//h1[@id='summary-val']");
    private final SelenideElement taskStatus = $x("//span[@id='status-val']");
    private final SelenideElement taskVersion = $x("//span[@id='fixVersions-field']");

    @Step("Проверка статуса и версии задачи")
    public void verifyTaskDetails(String expectedSummary, String expectedStatus, String expectedVersion) {
        assertEquals(expectedSummary, taskSummary.getText());
        assertEquals(expectedStatus, taskStatus.getText());
        assertEquals(expectedVersion, taskVersion.getText());
    }
}


