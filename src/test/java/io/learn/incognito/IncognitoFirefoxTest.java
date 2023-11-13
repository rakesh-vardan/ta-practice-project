package io.learn.incognito;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IncognitoFirefoxTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");

        driver = new FirefoxDriver(options);
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
