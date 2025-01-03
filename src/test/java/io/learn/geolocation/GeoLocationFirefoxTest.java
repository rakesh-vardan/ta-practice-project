package io.learn.geolocation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class GeoLocationFirefoxTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("geo.enabled", true);
        options.addPreference("geo.prompt.testing", true);
        options.addPreference("geo.prompt.testing.allow", true);
        options.addPreference("geo.provider.use_corelocation", true);
        options.addPreference("geo.wifi.uri", "data:application/json , { \"status\": \"OK\", \"accuracy\": 100.0, \"location\": { \"lat\": 18.975080, \"lng\": 72.825838, \"latitude\": 18.975080, \"longitude\": 72.825838, \"accuracy\": 100.0 } }");

        driver = new FirefoxDriver(options);
    }

    @Test
    public void testGeolocation() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/geolocation.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.findElement(By.id("get-coordinates")).click();
        WebElement coordinates = driver.findElement(By.id("coordinates"));
        wait.until(ExpectedConditions.visibilityOf(coordinates));
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        //pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(10).toMillis());

        driver.quit();
    }
}
