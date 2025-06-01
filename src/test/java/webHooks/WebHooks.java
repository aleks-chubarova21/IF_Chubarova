package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import pages.AuthPage;
import static com.codeborne.selenide.Selenide.open;
import static util.TestProperties.getProperty;

@Feature("Настройка тестового окружения")
public class WebHooks {
    @BeforeAll
    public static void setUpAllure() {
        boolean screenshots = Boolean.parseBoolean(getProperty("allure.screenshots"));
        boolean savePageSource = Boolean.parseBoolean(getProperty("allure.savePageSource"));

        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide()
                        .screenshots(screenshots)
                        .savePageSource(savePageSource)
        );
    }
    @BeforeEach
    @Story("Подготовка окружения")
    @Description("Настройка браузера и авторизация в системе")
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.headless = false;

        open(getProperty("url"));

        WebDriverRunner.getWebDriver().manage().window().maximize();

        new AuthPage().login(getProperty("login"), getProperty("password"));
    }

    @AfterEach
    @Story("Завершение теста")
    @Description("Закрытие браузера после выполнения теста")
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}