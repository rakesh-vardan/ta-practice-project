package io.learn.extensions;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static io.github.bonigarcia.wdm.WebDriverManager.zipFolder;
import static org.assertj.core.api.Assertions.assertThat;

public class FirefoxExtensionLoadTest {

    protected WebDriver driver;
    Path zippedExtension;

    @BeforeClass
    public void setUp() throws URISyntaxException {
        Path extensionFolder = Paths
                .get(ClassLoader.getSystemResource("web-extension").toURI());
        zippedExtension = zipFolder(extensionFolder);

        driver = WebDriverManager.firefoxdriver().create();
        ((FirefoxDriver) driver).installExtension(zippedExtension, true);
    }


    @Test
    void testAddExtension() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertThat(h1.getText())
                .isNotEqualTo("Hands-On Selenium WebDriver with Java");
    }

    @AfterClass
    public void tearDown() throws InterruptedException, IOException {
        //pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(10).toMillis());
        Files.delete(zippedExtension);
        driver.quit();
    }
}
