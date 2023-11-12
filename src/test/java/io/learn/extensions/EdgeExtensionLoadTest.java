package io.learn.extensions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class EdgeExtensionLoadTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() throws URISyntaxException {
        EdgeOptions options = new EdgeOptions();
        Path extension = Paths
                .get(ClassLoader.getSystemResource("web-extension").toURI());
        options.addArguments(
                "--load-extension=" + extension.toAbsolutePath());

        driver = new EdgeDriver(options);
    }


    @Test
    void testAddExtension() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        WebElement h1 = driver.findElement(By.tagName("h1"));
        assertThat(h1.getText())
                .isNotEqualTo("Hands-On Selenium WebDriver with Java");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        //pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(10).toMillis());
        driver.quit();
    }
}
