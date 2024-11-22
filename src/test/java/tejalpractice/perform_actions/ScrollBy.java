package tejalpractice.perform_actions;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ScrollBy {

    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        driver = new ChromeDriver();
    }

    @Test
    public void testScrollBy()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,1000)");
    }

    @Test
    public void testScrollIntoView()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement element = driver.findElement(By.xpath("//p[Contains(text(),'Curae id risus lacinia nisl lectus class']"));
        String script = "arguments[0].scrollIntoView();";
        js.executeScript(script,element);
    }


    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
