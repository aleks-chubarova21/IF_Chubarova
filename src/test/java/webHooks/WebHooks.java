package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import pages.AuthPage;
import static com.codeborne.selenide.Selenide.open;
import static util.TestProperties.getProperty;

public class WebHooks {

    @Before
    public void setUp() {
        Configuration.browser = "chrome";
        Configuration.timeout = 10000;
        Configuration.headless = false;

        open(getProperty("url"));
        WebDriverRunner.getWebDriver().manage().window().maximize();
        new AuthPage().login(getProperty("login"), getProperty("password"));
    }

    @After
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}