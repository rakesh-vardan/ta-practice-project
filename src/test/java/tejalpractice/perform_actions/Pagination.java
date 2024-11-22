package tejalpractice.perform_actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;


public class Pagination {

    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        driver = new ChromeDriver();
    }

    @Test
    public void navigateVerifyBody() throws InterruptedException {
         driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[text()='Navigation']")).click();
        driver.findElement(By.xpath("//a[text() = 'Next']")).click();
        driver.findElement(By.xpath("//a[text() = '3']")).click();
        driver.findElement(By.xpath("//a[text() = 'Previous']")).click();

        String bodyText = driver.findElement(By.xpath("//p[@className = 'lead']")).getText();
        /* ??? we are not using softassert here*/
        assertThat(bodyText).contains("Lorem ipsum");

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
