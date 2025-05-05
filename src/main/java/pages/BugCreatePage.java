package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;
import java.time.Duration;

public class BugCreatePage {
    private final SelenideElement createButton = $x("//*[@id='create_link']");
    private final SelenideElement issueTypeField = $x("//input[@id='issuetype-field']");
    private final SelenideElement summaryField = $x("//*[@id='summary']");
    private final SelenideElement createIssueButton = $x("//input[@id='create-issue-submit']");

    private final SelenideElement descriptionContainer = $x("//div[@id='description-wiki-edit']");
    private final SelenideElement descriptionTextButton = descriptionContainer.$x(".//button[contains(., 'Текст')]");
    private final SelenideElement descriptionField = descriptionContainer.$x(".//textarea");
    private final SelenideElement environmentContainer = $x("//div[@id='environment-wiki-edit']");
    private final SelenideElement environmentTextButton = environmentContainer.$x(".//button[contains(., 'Текст')]");
    private final SelenideElement environmentField = environmentContainer.$x(".//textarea");
    private final SelenideElement versionField = $x("//select[@id='versions']");

    public void createNewBug(String issueType, String summary, String description, String environment, String version) {
        createButton.shouldBe(interactable, Duration.ofSeconds(10)).click();

        issueTypeField.shouldBe(interactable, Duration.ofSeconds(5)).setValue(issueType);
        summaryField.shouldBe(interactable, Duration.ofSeconds(5)).setValue(summary);

        switchToVisualMode(descriptionTextButton, "Описание");
        descriptionField.shouldBe(interactable, Duration.ofSeconds(5)).setValue(description);

        switchToVisualMode(environmentTextButton, "Окружение");
        environmentField.shouldBe(interactable, Duration.ofSeconds(10)).setValue(environment);

        versionField.shouldBe(interactable, Duration.ofSeconds(10)).selectOption(version);

        createIssueButton.shouldBe(interactable, Duration.ofSeconds(10)).click();

    }
    private void switchToVisualMode(SelenideElement textButton, String fieldName) {
        textButton.shouldBe(visible, Duration.ofSeconds(10)).shouldBe(interactable).click();
    }
}