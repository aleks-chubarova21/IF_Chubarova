package tests;

import webHooks.WebHooks;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import static com.codeborne.selenide.Selenide.refresh;
import static org.junit.jupiter.api.Assertions.*;

@Feature("Работа с задачами в Jira")
public class JiraTest extends WebHooks {
    private final AuthPage authPage = new AuthPage();
    private final ProjectPage projectPage = new ProjectPage();
    private final TestProjectPage testProjectPage = new TestProjectPage();
    private final BugCreatePage bugCreatePage = new BugCreatePage();
    private final TestTaskPage testTaskPage = new TestTaskPage();
    private final TaskPage taskPage = new TaskPage();

    private static final String TO_DO = "СДЕЛАТЬ";
    private static final String IN_PROGRESS = "В РАБОТЕ";
    private static final String DONE = "ГОТОВО";

    private static final String EXISTING_TASK = "TestSeleniumATHomework";
    private static final String NEW_TASK_TITLE = "TestTestTest";
    private static final String TASK_DESCRIPTION = "Описание бага";
    private static final String ENVIRONMENT = "Windows 11";
    private static final String BUG_TYPE = "Ошибка";
    private static final String VERSION = "Version 2.0";

    @Test
    @DisplayName("Проверка успешной аутентификации")
    @Story("Авторизация")
    @Description("Проверка успешной аутентификации")
    void verifySuccessfulAuth() {
        assertTrue(authPage.isUserLoggedIn(), "Пользователь не авторизован");
    }

    @Test
    @DisplayName("Открытие проекта Test")
    @Story("Работа с проектом")
    @Description("Открытие проекта Test")
    void openTestProject() {
        projectPage.openTestProject();
        testProjectPage.verifyProjectOpened();
    }

    @Test
    @DisplayName("Проверка общего количества задач в проекте")
    @Story("Работа с проектом")
    @Description("Проверка общего количества задач в проекте")
    void verifyTasksCount() {
        projectPage.openTestProject();
        testProjectPage.verifyProjectOpened();
        assertTrue(testProjectPage.getTasksCount() > 0, "Количество задач должно быть больше 0");
    }

    @Test
    @DisplayName("Проверка статуса и версии задачи")
    @Story("Проверка задач")
    @Description("Проверка статуса и версии задачи")
    void verifyTaskDetails() {
        projectPage.searchForTask(EXISTING_TASK);
        testTaskPage.verifyTaskDetails(EXISTING_TASK, TO_DO, VERSION);
    }

    @Test
    @DisplayName("Создание нового бага и проверка счетчика")
    @Story("Создание задач")
    @Description("Создание нового бага и проверка счетчика")
    void createBugAndVerifyCounter() {
        projectPage.openTestProject();
        int initialCount = testProjectPage.getTasksCount();

        bugCreatePage.createNewBug(BUG_TYPE, NEW_TASK_TITLE, TASK_DESCRIPTION, ENVIRONMENT, VERSION);

        refresh();
        assertEquals(initialCount + 1, testProjectPage.getTasksCount(), "Количество задач должно увеличиться на 1");
    }

    @Test
    @DisplayName("Перевод бага по статусам до закрытия")
    @Story("Управление задачами")
    @Description("Перевод бага по статусам до закрытия")
    void transitionBugStatuses() {
        projectPage.searchForTask(NEW_TASK_TITLE);

        assertTrue(taskPage.verifyCurrentStatus(TO_DO), "Начальный статус должен быть 'СДЕЛАТЬ'");

        taskPage.clickInProgressButton();
        assertTrue(taskPage.verifyCurrentStatus(IN_PROGRESS), "Статус должен измениться на 'В РАБОТЕ'");

        taskPage.clickBusinessProcessAndDone();
        assertTrue(taskPage.verifyCurrentStatus(DONE), "Статус должен измениться на 'ГОТОВО'");
    }
}