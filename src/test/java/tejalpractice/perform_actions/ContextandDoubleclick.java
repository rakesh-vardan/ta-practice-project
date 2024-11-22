package tejalpractice.perform_actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ContextandDoubleclick {

    WebDriver driver;

    @BeforeClass
    public void setUp()
    {
        driver = new ChromeDriver();
    }

    @Test
    public void contextAndDoubleClick()
    {
        // ???? for all mouse actions always actions class is used.
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/dropdown-menu.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Actions actions = new Actions(driver);

        WebElement dropdown2 = driver.findElement(By.id("my-dropdown-2"));
        actions.contextClick(dropdown2).build().perform();
        WebElement contextMenu2 = driver.findElement(By.id("context-menu-2"));
        assertThat(contextMenu2.isDisplayed()).isTrue();

        WebElement dropdown3 = driver.findElement(By.id("my-dropdown-3"));
        actions.doubleClick(dropdown3).build().perform();
        WebElement contextMenu3 = driver.findElement(By.id("context-menu-3"));
        assertThat(contextMenu3.isDisplayed()).isTrue();

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
