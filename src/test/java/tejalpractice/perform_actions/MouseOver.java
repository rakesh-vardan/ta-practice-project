package tejalpractice.perform_actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MouseOver {

    WebDriver driver;

    @BeforeClass
    public void setUp()
    {
       driver = new ChromeDriver();
    }

    @Test
    public void testMouseOverList()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        Actions action = new Actions(driver);

        List<String> imageList = Arrays.asList("compass", "calendar", "award", "landscape");
        for(String imageName:imageList)
        {
            String xpath = String.format("//img[@src='img/%s.png']",imageName);
            WebElement image = driver.findElement(By.xpath(xpath));
            action.moveToElement(image).build().perform();
            WebElement imagecaption = driver.findElement(RelativeLocator.with(By.tagName("p")).near(image));

            assertThat(imagecaption.getText()).containsIgnoringCase(imageName);
        }
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
