package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import pages.AuthPage;
import static com.codeborne.selenide.Selenide.open;
import static util.TestProperties.getProperty;

public class WebHooks {

    @BeforeEach
    public void setUp() {
        // Настройка Selenide
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000;
        Configuration.headless = false;
        
        // Открытие страницы и авторизация
        open(getProperty("url"));
        new AuthPage().login(getProperty("login"), getProperty("password"));
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}

