package io.learn.network_monitor;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v131.network.Network;
import org.openqa.selenium.devtools.v131.network.model.Headers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Log4j2
public class NetworkMonitoringChromeTest {

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
    public void testNetworkMonitoring() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), request -> {
            log.info("Request {}", request.getRequestId());
            log.info("\t Method: {}", request.getRequest().getMethod());
            log.info("\t URL: {}", request.getRequest().getUrl());
            logHeaders(request.getRequest().getHeaders());
        });

        devTools.addListener(Network.responseReceived(), response -> {
            log.info("Response {}", response.getRequestId());
            log.info("\t URL: {}", response.getResponse().getUrl());
            log.info("\t Status: {}", response.getResponse().getStatus());
            logHeaders(response.getResponse().getHeaders());
        });

        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }

    void logHeaders(Headers headers) {
        log.debug("\t Headers:");
        headers.toJson().forEach((k, v) -> log.debug("\t\t{}:{}", k, v));
    }

}
