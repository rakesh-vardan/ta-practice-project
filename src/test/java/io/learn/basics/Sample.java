package io.learn.basics;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class Sample {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testBasicMethods() {
        String appURL = "https://bonigarcia.dev/selenium-webdriver-java/";
        driver.get(appURL);

        assertThat(driver.getTitle())
                .isEqualTo("Hands-On Selenium WebDriver with Java");
        assertThat(driver.getCurrentUrl()).isEqualTo(appURL);
        assertThat(driver.getPageSource()).containsIgnoringCase("</html>");
    }

    @Test
    void testSessionId() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
        assertThat(sessionId).isNotNull();
        log.debug("The sessionId is {}", sessionId.toString());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
