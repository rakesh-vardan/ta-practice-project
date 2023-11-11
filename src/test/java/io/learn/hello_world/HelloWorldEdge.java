package io.learn.hello_world;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class HelloWorldEdge {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new EdgeDriver();
    }

    @Test
    public void verifyTitle() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).isEqualTo("Hands-On Selenium WebDriver with Java");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
