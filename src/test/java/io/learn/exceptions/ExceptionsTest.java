package io.learn.exceptions;

import io.learn.BaseTest;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class ExceptionsTest extends BaseTest {

    @Test
    public void testNoSuchElementException() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement textInput = driver.findElement(By.id("my-text-is"));

        // Fix by updating the correct locator
        // WebElement textInput = driver.findElement(By.id("my-text-id"));
        textInput.sendKeys("Hello World");
    }

    @Test
    void testNoSuchElementException2() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/loading-images.html");

        // Fix by adding waits
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement landscape = driver.findElement(By.id("landscape"));
        assertThat(landscape.getDomAttribute("src"))
                .containsIgnoringCase("landscape");
    }

    @Test
    public void testElementNotInteractableException() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        WebElement hiddenElement = driver
                .findElement(By.cssSelector("input[type=hidden]"));
        hiddenElement.click();

        // handle it using try catch blocks

        /*
                try {
                    hiddenElement.click();
                } catch (ElementNotInteractableException exception) {
                    System.out.println("Unable to click the element");
                    // rest of the steps
                }
        */
    }

    @Test
    void testNoAlertPresentException() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");

//        driver.findElement(By.id("my-alert")).click();
        Alert alert = driver.switchTo().alert();
        assertThat(alert.getText()).isEqualTo("Hello world!");
        alert.accept();
    }

    @Test
    void testNoSuchSessionException() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java");
        driver.quit();

        WebElement textInput = driver.findElement(By.id("my-text-id"));
        textInput.sendKeys("Hello World");
    }

    @Test
    void testInvalidSelectorException() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java");
        WebElement textInput = driver.findElement(By.xpath("#my-text-id"));
        textInput.sendKeys("Hello World");
    }

    @Test
    public void testStaleElementReferenceException() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement returnToIndex = driver.findElement(By.linkText("Return to index"));
        returnToIndex.click();

        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).contains("index.html");
        driver.findElement(By.xpath("//a[normalize-space()='Navigation']")).click();

        returnToIndex.click();
    }
}
