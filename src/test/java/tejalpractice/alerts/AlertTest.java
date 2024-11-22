package tejalpractice.alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class AlertTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testGetTextAccept()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.id("my-alert")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alt =  driver.switchTo().alert();
        assertThat(alt.getText()).isEqualTo("Hello world!");
        alt.accept();
    }

    @Test
    public void testDismiss()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.findElement(By.id("my-confirm")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert confirm = driver.switchTo().alert();
        assertThat(confirm.getText()).isEqualTo("Is this correct?");
        confirm.dismiss();
    }

    @Test
    void testPrompt()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.findElement(By.id("my-prompt")).click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert prompt = driver.switchTo().alert();
        prompt.accept();
    }

    @Test
    void testModal()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dialog-boxes.html");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.findElement(By.id("my-modal")).click();
        WebElement close = driver.findElement(By.xpath("//button[text()='Close']"));
        wait.until(ExpectedConditions.elementToBeClickable(close));
        assertThat(close.getTagName()).isEqualTo("button");
        close.click();
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
