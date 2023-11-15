package io.learn.emulate_network;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.network.Network;
import org.openqa.selenium.devtools.v119.network.model.ConnectionType;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
public class EmulateNetworkConditionChromeTest {

    WebDriver driver;

    DevTools devTools;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
    }

    @AfterMethod
    public void teardown() {
        devTools.close();
        driver.quit();
    }

    @Test
    public void testEmulateNetworkConditions() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 100, 50 * 1024,
                50 * 1024, Optional.of(ConnectionType.CELLULAR3G)));

        long initMillis = System.currentTimeMillis();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        Duration elapsed = Duration
                .ofMillis(System.currentTimeMillis() - initMillis);
        log.info("The page took {} ms to be loaded", elapsed.toMillis());

        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

    @Test
    public void testInNormalConditions() {
        long initMillis = System.currentTimeMillis();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        Duration elapsed = Duration
                .ofMillis(System.currentTimeMillis() - initMillis);
        log.info("The page took {} ms to be loaded", elapsed.toMillis());

        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
}
