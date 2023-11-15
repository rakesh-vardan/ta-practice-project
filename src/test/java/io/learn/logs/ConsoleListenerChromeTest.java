package io.learn.logs;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.events.ConsoleEvent;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Log4j2
public class ConsoleListenerChromeTest {

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
    public void testConsoleListener() throws Exception {
        CompletableFuture<ConsoleEvent> futureEvents = new CompletableFuture<>();
        devTools.getDomains().events()
                .addConsoleListener(futureEvents::complete);

        CompletableFuture<JavascriptException> futureJsExc = new CompletableFuture<>();
        devTools.getDomains().events()
                .addJavascriptExceptionListener(futureJsExc::complete);

        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/console-logs.html");

        ConsoleEvent consoleEvent = futureEvents.get(5, TimeUnit.SECONDS);
        log.info("ConsoleEvent: {} {} {}", consoleEvent.getTimestamp(),
                consoleEvent.getType(), consoleEvent.getMessages());

        JavascriptException jsException = futureJsExc.get(5, TimeUnit.SECONDS);
        log.info("JavascriptException: {} {}", jsException.getMessage(),
                jsException.getSystemInformation());
    }
}
