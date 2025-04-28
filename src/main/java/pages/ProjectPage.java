package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.$x;
import java.time.Duration;

public class ProjectPage {
    private final SelenideElement projectsMenu = $x("//a[@id='browse_link']")
            .as("Меню проектов");
    private final SelenideElement testProjectLink = $x("//a[@id='admin_main_proj_link_lnk']")
            .as("Ссылка на тестовый проект");
    private final SelenideElement createButton = $x("//a[@id='create_link']")
            .as("Кнопка создания задачи");
    private final SelenideElement searchInput = $x("//input[@id='quickSearchInput']")
            .as("Поле поиска");
    private final SelenideElement createIssueDialog = $x("//div[@id='create-issue-dialog']");

    public void openTestProject() {
        projectsMenu.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        testProjectLink.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
    }

    public void clickCreateIssue() {
        createButton.shouldBe(Condition.visible, Duration.ofSeconds(10)).click();
        createIssueDialog.shouldBe(Condition.visible, Duration.ofSeconds(10));
    }

    public void searchForTask(String taskName) {
        searchInput.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(taskName).pressEnter();
    }
}