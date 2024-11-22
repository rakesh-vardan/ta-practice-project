package tejalpractice.iframes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.bouncycastle.oer.its.template.ieee1609dot2.basetypes.Ieee1609Dot2BaseTypes.Duration;

public class IframeTest {

    WebDriver driver;
    @BeforeClass
    public void setUp()
    {
        driver = new ChromeDriver();
    }

    @Test
    public void testIframes()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/iframes.html");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.frameTobeAvailableAndSwitchToIt("my-iframe"));
        //By ????
        By pName = By.tagName("p");
        // 0 ????
        wait.until(ExpectedConditions.numberofElementsToBeMoreThan(pName,0));
        List<WebElement> paragraphs = driver.findElements(pName);
        //????20
        assertThat(paragraphs).hasSize(20);
    }

    @Test
    void testFrames()
    { driver.get("https://bonigarcia.dev/selenium-webdriver-java/frames.html");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        String frameName = "frame-body";
        wait.until(ExpectedConditions.presenceofElementLocated(By.name(frameName)));
        driver.switchTo().frame(frameName);
        By pName = By.tagName("p");
        wait.until(ExpectedConditions.numberofElementsToBeMoreThan(pName,0));
        List<WebElement> paragraphs = driver.findElements(pName);
        assertThat(paragraphs).hasSize(20);
        driver.switchTo().defaultContent();
    }

    @AfterClass
    public void tearDown()
    {
          driver.quit();
    }
}
