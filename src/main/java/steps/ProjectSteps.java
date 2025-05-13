package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.*;
import util.TestProperties;

import static com.codeborne.selenide.Selenide.*;

public class ProjectSteps {
    private final AuthPage authPage = new AuthPage();
    private final ProjectPage projectPage = new ProjectPage();
    private final TestProjectPage testProjectPage = new TestProjectPage();
    private final BugCreatePage bugCreatePage = new BugCreatePage();
    private final TaskPage taskPage = new TaskPage();

    private final String BUG_TYPE = "Ошибка";
    private final String NEW_TASK_TITLE = "TestTestTest";
    private final String TASK_DESCRIPTION = "Описание бага";
    private final String ENVIRONMENT = "Windows 11";
    private final String VERSION = "Version 2.0";
    private int initialTasksCount;

    @Дано("Пользователь авторизован как {string} с паролем {string}")
    public void loginUser(String username, String password) {
        authPage.open(TestProperties.getProperty("url"));
        authPage.login(username, password);
    }

    @Когда("Перейти в проект Test")
    public void openTestProject() {
        projectPage.openTestProject();
        testProjectPage.verifyProjectOpened();
    }

    @Когда("Пользователь запоминает текущее количество задач")
    public void rememberTasksCount() {
        initialTasksCount = testProjectPage.getTasksCount();
    }

    @Когда("Пользователь создает новый баг")
    public void createNewBug() {
        bugCreatePage.createNewBug(BUG_TYPE, NEW_TASK_TITLE, TASK_DESCRIPTION, ENVIRONMENT, VERSION);
        sleep(2000);
        refresh();
    }

    @Тогда("Количество задач увеличивается на {int}")
    public void verifyTasksCountIncreased(int expectedIncrease) {
        int currentCount = testProjectPage.getTasksCount();
        Assertions.assertEquals(
                initialTasksCount + expectedIncrease, currentCount,"Количество задач должно увеличиться на " + expectedIncrease
        );
    }

    @Когда("Найти и открыть баг {string}")
    public void findAndOpenBug(String bugName) {
        projectPage.searchForTask(bugName);
    }

    @Когда("Перевести баг в статус {string}")
    public void transitionBugStatus(String targetStatus) {
        switch(targetStatus) {
            case "В РАБОТЕ":
                taskPage.clickInProgressButton();
                break;
            case "ГОТОВО":
                taskPage.clickBusinessProcessAndDone();
                break;
        }
    }

    @Когда("Найти и открыть задачу TestSeleniumATHomework")
    public void openTestSeleniumTask(){
        projectPage.searchForTask("TestSeleniumATHomework");
    }
    @Тогда("Проверить статус и версию")
    public void verifyTaskDetails(){
        new TestTaskPage().verifyTaskDetails(
                "TestSeleniumATHomework", "СДЕЛАТЬ", "Version 2.0");
    }
}