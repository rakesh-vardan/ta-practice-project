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

import static org.assertj.core.api.Assertions.assertThat;

public class CheckboxRadiobutton {


    WebDriver driver;

    @BeforeClass
      public void setUp()
      {
          driver = new ChromeDriver();

      }

      @Test
      public void verifyIsSelected() throws InterruptedException {
          driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
          driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
          driver.manage().window().maximize();

          WebElement checkbox1 = driver.findElement(By.id("my-check-1"));
          checkbox1.click();

          assertThat(checkbox1.isSelected()).isFalse();

          WebElement radioButton2 = driver.findElement(By.id("my-radio-2"));
          // radioButton2.click();
          // ???? radiobutton generally always need javascript excut or try click first
          //???? arguments[0]
          JavascriptExecutor js = (JavascriptExecutor)driver;
          js.executeScript("arguments[0].click();",radioButton2);

          assertThat(radioButton2.isSelected()).isTrue();
      }

      @AfterClass
      public void tearDown()
      {
          driver.quit();
      }

}
