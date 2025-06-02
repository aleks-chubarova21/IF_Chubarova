package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;

public class BugCreatePage {
    private final SelenideElement createButton = $x("//*[@id='create_link']");
    private final SelenideElement issueTypeField = $x("//input[@id='issuetype-field']");
    private final SelenideElement summaryField = $x("//*[@id='summary']");
    private final SelenideElement createIssueButton = $x("//input[@id='create-issue-submit']");
    private final SelenideElement descriptionField = $x("//textarea[@name='description']");
    private final SelenideElement environmentField = $x("//textarea[@name='environment']");
    private final SelenideElement versionField = $x("//select[@id='versions']");
    private final SelenideElement textModeButton = $x("//button[contains(., 'Текст')]");

    @Step("Открытие формы создания задачи")
    public void openCreateForm() {
        createButton
                .shouldBe(interactable)
                .click();
    }

    @Step("Создание новой задачи типа '{issueType}' с заголовком '{summary}'")
    public void createNewBug(String issueType, String summary,
                             String description, String environment,
                             String version) {
        openCreateForm();
        setIssueType(issueType);
        setSummary(summary);
        setDescription(description);
        setEnvironment(environment);
        selectVersion(version);
        submitCreation();
    }

    @Step("Установка типа задачи: {issueType}")
    private void setIssueType(String issueType) {
        issueTypeField
                .shouldBe(interactable)
                .setValue(issueType);
    }

    @Step("Установка заголовка задачи")
    private void setSummary(String summary) {
        summaryField
                .shouldBe(interactable)
                .setValue(summary);
    }

    @Step("Заполнение описания задачи")
    private void setDescription(String description) {
        ensureTextMode(descriptionField);
        descriptionField
                .shouldBe(interactable)
                .setValue(description);
    }

    @Step("Заполнение окружения")
    private void setEnvironment(String environment) {
        ensureTextMode(environmentField);
        environmentField
                .shouldBe(interactable)
                .setValue(environment);
    }

    @Step("Выбор версии: {version}")
    private void selectVersion(String version) {
        versionField
                .shouldBe(interactable)
                .selectOption(version);
    }

    @Step("Подтверждение создания задачи")
    private void submitCreation() {
        createIssueButton
                .shouldBe(interactable)
                .click();
    }

    @Step("Переключение в текстовый режим")
    private void ensureTextMode(SelenideElement field) {
        if (!field.isDisplayed()) {
            textModeButton
                    .shouldBe(visible)
                    .click();
        }
    }
}