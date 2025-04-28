package tests;

import webHooks.WebHooks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import com.codeborne.selenide.Selenide;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JiraTest extends WebHooks {

    private final AuthPage authPage = new AuthPage();
    private final ProjectPage projectPage = new ProjectPage();
    private final TestProjectPage testProjectPage = new TestProjectPage();
    private final BugCreatePage bugCreatePage = new BugCreatePage();
    private final TestTaskPage testTaskPage = new TestTaskPage();
    private final TaskPage taskPage = new TaskPage();

    private final String EXISTING_TASK = "TestSeleniumATHomework";
    private final String NEW_TASK_TITLE = "TestTestTest";
    private final String TASK_DESCRIPTION = "Описание бага";
    private final String ENVIRONMENT = "Windows 11";
    private final String BUG_TYPE = "Ошибка";
    private final String VERSION = "Version 2.0";
    private final String TO_DO_STATUS = "СДЕЛАТЬ";
    private final String IN_PROGRESS_STATUS = "В РАБОТЕ";
    private final String DONE_STATUS = "ГОТОВО";

    @DisplayName("Проверка успешной аутентификации")
    @Test
    public void verifySuccessfulAuth() {
        assertTrue(authPage.isUserLoggedIn(), "Пользователь не авторизован");
    }

    @DisplayName("Открытие проекта Test")
    @Test
    public void goToTestProject() {
        projectPage.openTestProject();
        testProjectPage.verifyProjectOpened();
    }

    @DisplayName("Проверка общего количества задач в проекте")
    @Test
    public void checkTasksCount() {
        projectPage.openTestProject();
        testProjectPage.verifyProjectOpened();
        int taskCount = testProjectPage.getTasksCount();
        assertTrue(taskCount > 0, "Количество задач должно быть больше 0");
    }

    @DisplayName("Проверка статуса и версии задачи")
    @Test
    public void checkTestSeleniumTask() {
        projectPage.searchForTask(EXISTING_TASK);
        testTaskPage.verifyTaskDetails(EXISTING_TASK, TO_DO_STATUS, VERSION);
    }

    @DisplayName("Создание нового бага и проверка счетчика")
    @Test
    public void createNewBugAndVerifyCount() {
        projectPage.openTestProject();
        int startNumTask = testProjectPage.getTasksCount();

        bugCreatePage.createNewBug(BUG_TYPE, NEW_TASK_TITLE, TASK_DESCRIPTION, ENVIRONMENT, VERSION);

        Selenide.refresh();
        int endNumTask = testProjectPage.getTasksCount();
        assertEquals(startNumTask + 1, endNumTask, "количество должно увеличиться на 1");
    }
    
    @DisplayName("Перевод бага по статусам до закрытия")
    @Test
    public void transitionBugThroughStatuses() {
        projectPage.searchForTask(NEW_TASK_TITLE);

        assertTrue(taskPage.verifyCurrentStatus(TO_DO_STATUS), "Начальный статус не 'СДЕЛАТЬ'");

        taskPage.clickInProgressButton();
        assertTrue(taskPage.verifyCurrentStatus(IN_PROGRESS_STATUS), "Статус не изменился на 'В РАБОТЕ'");

        taskPage.clickBusinessProcessAndDone();
        assertTrue(taskPage.verifyCurrentStatus(DONE_STATUS), "Статус не изменился на 'ГОТОВО'");
    }
}