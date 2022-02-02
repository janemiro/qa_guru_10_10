import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Configuration.browser;

public class TestBase {

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        String login = System.getProperty("login");
        String password = System.getProperty("password");
        String url = System.getProperty("url");
        String browser = System.getProperty("browser");
        String browserVersion = System.getProperty("version");
        String remoteUrl = "https://" + login + ":" + password + "@" + url;

        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion; // available versions: chrome - 90, 91 opera - 76, 77 firefox - 88, 89
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "2560x1440";
        Configuration.remote = remoteUrl;
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        // Attach.attachAsText("Browser: ", browser);
        //Attach.attachAsText("Version: ", version);
        // Attach.attachAsText("Remote Url: ", remoteUrl);
    }







    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}