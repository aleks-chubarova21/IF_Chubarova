package pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.Selenide;

public class BugCreatePage {
    private final SelenideElement createButton = $x("//*[@id='create_link']")
            .as("Кнопка Создать");
    private final SelenideElement issueTypeField = $x("//input[@id='issuetype-field']")
            .as("Поле типа задачи");
    private final SelenideElement summaryField = $x("//*[@id='summary']")
            .as("Тема задачи");
    private final SelenideElement descriptionField = $x("//div[@id='description-wiki-edit']//textarea")
            .as("Поле описания");
    private final SelenideElement environmentField = $x("//div[@id='environment-wiki-edit']//textarea")
            .as("Поле окружения");
    private final SelenideElement versionField = $x("//select[@id='versions']")
            .as("Поле версии");
    private final SelenideElement createIssueButton = $x("//input[@id='create-issue-submit']")
            .as("Кнопка Создать (подтверждение)");
    private final SelenideElement closeNotificationButton = $x("//button[@title='Закрыть']")
            .as("Кнопка закрытия уведомления");
    private final SelenideElement taskCounter = $x("//div[@class='showing']//span")
            .as("Счетчик задач");

    public void createNewBug(String issueType, String summary, String description, String environment, String version) {
        createButton.shouldBe(visible).click();
        Selenide.sleep(1000); // Ждем открытия формы

        issueTypeField.shouldBe(visible).setValue(issueType);
        Selenide.sleep(500);

        summaryField.shouldBe(visible).setValue(summary);
        Selenide.sleep(500);

        $x("//div[@id='description-wiki-edit']//button[contains(text(), 'Текст')]")
                .shouldBe(visible).click();
        Selenide.sleep(500);

        descriptionField.shouldBe(visible, enabled).setValue(description);
        Selenide.sleep(500);

        $x("//div[@id='environment-wiki-edit']//button[contains(text(), 'Текст')]")
                .shouldBe(visible).click();
        Selenide.sleep(500);

        environmentField.shouldBe(visible, enabled).setValue(environment);
        Selenide.sleep(500);
        
        versionField.shouldBe(visible, enabled).selectOption(version);
        Selenide.sleep(500);

        createIssueButton.shouldBe(visible, enabled).click();
        Selenide.sleep(3000);

        closeTaskCreationNotification();
        Selenide.refresh();
        taskCounter.shouldBe(visible);
    }

    private void closeTaskCreationNotification() {
        if (closeNotificationButton.isDisplayed()) {
            closeNotificationButton.click();
        }
    }
}