package tejalpractice.screenshots;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class PageSectionScreenshot {

    WebDriver driver;

    @BeforeClass
    public void setUp()
    {
      driver = new ChromeDriver();
    }

    @Test
    public void pageSection() throws InterruptedException, IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        driver.manage().window().maximize();
        Thread.sleep(10000);
       WebElement section =  driver.findElement(By.xpath("//div[@className='col-md-4 py-2']"));
        File src = section.getScreenshotAs(OutputType.FILE);
        File trg = new File(".\\screenshots\\homepage.png");
        FileUtils.copyFile(src,trg);
    }

    @AfterClass
    public void tearDown()
    {
      driver.quit();
    }
}
