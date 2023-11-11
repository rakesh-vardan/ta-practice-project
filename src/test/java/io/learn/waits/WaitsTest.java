package io.learn.waits;

import io.learn.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class WaitsTest extends BaseTest {

    @Test
    void testImplicitWait() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement landscape = driver.findElement(By.id("landscape"));
        assertThat(landscape.getAttribute("src"))
                .containsIgnoringCase("landscape");
    }

    @Test
    void testExplicitWait() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement landscape = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("landscape")));
        assertThat(landscape.getAttribute("src"))
                .containsIgnoringCase("landscape");
    }

    @Test
    void testSlowCalculator() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/slow-calculator.html");

        // 1 + 3
        driver.findElement(By.xpath("//span[text()='1']")).click();
        driver.findElement(By.xpath("//span[text()='+']")).click();
        driver.findElement(By.xpath("//span[text()='3']")).click();
        WebElement element = driver.findElement(By.xpath("//span[text()='=']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();", element);

        // ... should be 4, wait for it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBe(By.className("screen"), "4"));
    }

    @Test
    void testFluentWait() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        WebElement landscape = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.id("landscape")));
        assertThat(landscape.getAttribute("src"))
                .containsIgnoringCase("landscape");
    }
}
