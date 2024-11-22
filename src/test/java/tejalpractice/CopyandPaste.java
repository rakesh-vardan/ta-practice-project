package tejalpractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CopyandPaste {

    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        driver = new ChromeDriver();
    }

    // ???????
    @Test
    public void testCopyPaste()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        Actions action = new Actions(driver);

       WebElement inputText =  driver.findElement(By.id("my-text-id"));
       WebElement textArea  =  driver.findElement(By.name("my-textarea"));

       action.sendKeys(inputText,"hello world").keyDown(keys.CONTROL).sendKeys("A").keyUp(keys.CONTROL).build().perform();
       action.keyDown(keys.CONTROL).sendKeys("C").keyUp(keys.CONTROL).build().perform();
       action.sendKeys(textArea,"pasted text").keyDown(keys.CONTROL).sendKeys("V").keyUp(keys.CONTROL).build().perform();

       assertThat(inputText.getAttribute("value")).isEqualTo(textArea.getAttribute("value"));
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
