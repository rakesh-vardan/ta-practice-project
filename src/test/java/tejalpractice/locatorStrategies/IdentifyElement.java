package tejalpractice.locatorStrategies;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IdentifyElement {

    WebDriver driver;

    @BeforeClass
    void setUp()
    {
        driver = new ChromeDriver();
    }

    @Test
    void testByTagName()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement textArea = driver.findElement(By.tagName("textarea"));
        assertThat(textArea.getDomAttribute("rows")).isEqualTo("3");
    }

    @Test
    void testByHtmlAttributes()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        //By name
        WebElement textByName = driver.findElement(By.name("my-textarea"));

    }

    @AfterClass
    void tearDown()
    {
        driver.quit();
    }
}
