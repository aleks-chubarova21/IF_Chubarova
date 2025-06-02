package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class ProjectPage {
    private final SelenideElement projectsMenu = $x("//a[@id='browse_link']");
    private final SelenideElement testProjectLink = $x("//a[@id='admin_main_proj_link_lnk']");
    private final SelenideElement searchInput = $x("//input[@id='quickSearchInput']");

    @Step("Открыть тестовый проект")
    public void openTestProject() {
        openProjectsDropdown();
        selectTestProject();
    }

    @Step("Найти задачу '{taskName}'")
    public void searchForTask(String taskName) {
        searchInput
                .shouldBe(visible)
                .setValue(taskName)
                .pressEnter();
    }

    @Step("Открыть меню проектов")
    private void openProjectsDropdown() {
        projectsMenu
                .shouldBe(visible)
                .click();
    }

    @Step("Выбрать тестовый проект")
    private void selectTestProject() {
        testProjectLink
                .shouldBe(visible)
                .click();
    }
}