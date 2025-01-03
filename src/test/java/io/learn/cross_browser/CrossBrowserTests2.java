package io.learn.cross_browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CrossBrowserTests2 {

    // ThreadLocal variable to hold WebDriver instances for each thread
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    // Get the current thread's WebDriver instance
    private WebDriver getDriver() {
        return driver.get();
    }

    @Test
    @Parameters("browser")
    public void testTitle(String browser) {
        System.out.println("The thread ID for "+browser+" is "+ Thread.currentThread().getId());
        this.initializeDriver(browser);  // Initialize the WebDriver based on the browser
        getDriver().get("https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(getDriver().getTitle()).contains("Selenium WebDriver");
    }

    // Clean up WebDriver after each test method
    @AfterMethod
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();  // Quit the driver for the current thread
            driver.remove();     // Remove the thread-local reference
        }
    }

    // Initialize WebDriver based on the provided browser type and set it for the current thread
    private void initializeDriver(String browser) {
        WebDriver localDriver;
        if (browser.equalsIgnoreCase("chrome")) {
            localDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            localDriver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            localDriver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        localDriver.manage().window().maximize();
        driver.set(localDriver);  // Set the WebDriver for the current thread
    }
}
