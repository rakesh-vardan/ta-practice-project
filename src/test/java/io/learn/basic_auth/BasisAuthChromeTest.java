package io.learn.basic_auth;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class BasisAuthChromeTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        //wait for manual browser inspection
        Thread.sleep(Duration.ofSeconds(10).toMillis());
        driver.quit();
    }

    @Test
    public void testBasicAuth() {
        ((HasAuthentication) driver)
                .register(() -> new UsernameAndPassword("guest", "guest"));

        driver.get("https://jigsaw.w3.org/HTTP/Basic/");

        WebElement body = driver.findElement(By.tagName("body"));
        assertThat(body.getText()).contains("Your browser made it!");
    }

    @Test
    public void testBasicAuthOld() {
        driver.get("https://guest:guest@jigsaw.w3.org/HTTP/Basic/");

        WebElement body = driver.findElement(By.tagName("body"));
        assertThat(body.getText()).contains("Your browser made it!");
    }
}
