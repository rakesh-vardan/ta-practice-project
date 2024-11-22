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

import static org.assertj.core.api.Assertions.assertThat;

public class MouseOver1 {

    WebDriver driver;

   @BeforeClass
   public void setUp()
   {
       driver = new ChromeDriver();
   }

    @Test
     public void testmouseover1() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/mouse-over.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        Actions actions = new Actions(driver);

        WebElement image = driver.findElement(By.xpath("//img[@src='img/award.png']"));
        actions.moveToElement(image).build().perform();
        WebElement imagecaption = driver.findElement(RelativeLocator.with(By.tagName("p")).near(image));
        assertThat(imagecaption.getText()).containsIgnoringCase("Award");
    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
