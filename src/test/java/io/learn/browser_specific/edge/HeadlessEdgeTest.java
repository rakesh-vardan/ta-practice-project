package io.learn.browser_specific.edge;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HeadlessEdgeTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless=new");
        driver = new EdgeDriver(options);
    }


    @Test
    void testHeadless() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
