package tejalpractice.screenshots;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebElementScreenshot {

     WebDriver driver;

    @BeforeClass
    public void setUp()
    {
       driver = new ChromeDriver();
    }

    @Test
    public void elementScreenshot()
    {




    }

    @AfterClass
    public void tearDown()
    {
          driver.quit();
    }


}
