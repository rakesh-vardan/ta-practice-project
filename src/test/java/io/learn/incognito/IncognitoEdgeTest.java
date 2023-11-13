package io.learn.incognito;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IncognitoEdgeTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--incognito");

        driver = new EdgeDriver(options);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testIncognito() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
}
