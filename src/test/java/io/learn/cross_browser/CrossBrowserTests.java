package io.learn.cross_browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CrossBrowserTests {

    WebDriver driver;

    @Test(dataProvider = "getBrowserData")
    public void testTitle(String browser) {
        this.initializeDriver(browser);
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

    @DataProvider
    public Object[][] getBrowserData() {
        return new Object[][] {
                { "chrome" },
                { "edge" },
                { "firefox" } };
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    private void initializeDriver(String browser) {
        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if(browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if(browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        driver.manage().window().maximize();
    }
}
