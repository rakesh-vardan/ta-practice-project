package io.learn.mobile_emulation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class EdgeMobileTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone 6/7/8");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);

        driver = new EdgeDriver(options);
    }


    @Test
    void testMobileView() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
