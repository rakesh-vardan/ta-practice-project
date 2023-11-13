package io.learn.l10n;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalizationChromeTest2 {

    private WebDriver driver;
    private String lang;

    @BeforeMethod
    public void setup() {
        lang = "es-ES";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--lang=" + lang);

        driver = new ChromeDriver(options);
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        //pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(10).toMillis());
        driver.quit();
    }

    @Test
    public void testAcceptLang() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/multilanguage.html");

        ResourceBundle strings = ResourceBundle.getBundle("strings",
                Locale.forLanguageTag(lang));
        String home = strings.getString("home");
        String content = strings.getString("content");
        String about = strings.getString("about");
        String contact = strings.getString("contact");

        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertThat(bodyText).contains(home).contains(content).contains(about)
                .contains(contact);
    }
}
