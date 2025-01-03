package io.learn.grid;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTests {

    private WebDriver driver;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://localhost:4444/"),
                new ChromeOptions());

        driver = new RemoteWebDriver(new URL("http://13.233.193.203:4444/"),
                new ChromeOptions());
    }

    @Test
    public void testLengthyTest() throws InterruptedException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");

        Thread.sleep(3000);
        driver.findElement(By.name("my-text")).sendKeys("Hello World!");

        Thread.sleep(3000);
        WebElement checkbox1 = driver.findElement(By.cssSelector("[type=checkbox]:checked"));
        assertThat(checkbox1.getDomAttribute("id")).isEqualTo("my-check-1");
        assertThat(checkbox1.isSelected()).isTrue();

        WebElement checkbox2 = driver.findElement(By.cssSelector("[type=checkbox]:not(:checked)"));
        assertThat(checkbox2.getDomAttribute("id")).isEqualTo("my-check-2");
        assertThat(checkbox2.isSelected()).isFalse();

        checkbox1.click();
        assertThat(checkbox1.isSelected()).isFalse();

        checkbox2.click();
        assertThat(checkbox2.isSelected()).isTrue();

        Thread.sleep(3000);
        WebElement radio1 = driver.findElement(By.xpath("//input[@type='radio' and @checked]"));
        assertThat(radio1.getDomAttribute("id")).isEqualTo("my-radio-1");
        assertThat(radio1.isSelected()).isTrue();

        WebElement radio2 = driver.findElement(By.xpath("//input[@type='radio' and not(@checked)]"));
        assertThat(radio2.getDomAttribute("id")).isEqualTo("my-radio-2");
        assertThat(radio2.isSelected()).isFalse();

        radio2.click();
        assertThat(radio1.isSelected()).isFalse();
        assertThat(radio2.isSelected()).isTrue();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(20000);
        driver.quit();
    }
}
