package by.kvach.lightBase.config;

import by.kvach.lightBase.page.LoginPage;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.open;

public class ConfigTest {

    public void configLightBase() {

        Configuration.baseUrl = " ";
        Configuration.headless = true;
        Configuration.timeout = 6000;
    }

    @BeforeMethod(alwaysRun = true)
    public void init() {
        open("/login");
        LoginPage loginPage = new LoginPage();
        loginPage.signInByManager();
    }

    @AfterTest(alwaysRun = true)
    public void close() {
        Selenide.closeWebDriver();
    }
}
