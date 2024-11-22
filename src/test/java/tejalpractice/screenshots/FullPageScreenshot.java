package tejalpractice.screenshots;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class FullPageScreenshot {

    WebDriver driver;

    @BeforeClass
    public void setUp()
    {
       driver = new ChromeDriver();
    }

    @Test
    public void testFullScreenshot() throws IOException {
          driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
          TakesScreenshot ts = (TakesScreenshot)driver;
          File sourceScreenshot = ts.getScreenshotAs(OutputType.FILE);
          File target = new File(".\\Tejal\\homepage.png");
          FileUtils.copyFile(sourceScreenshot,target);
    }

    @AfterClass
     public void tearDown()
     {
         driver.quit();
     }
}






