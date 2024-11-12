package io.learn.cookies;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.network.Network;
import org.openqa.selenium.devtools.v127.network.model.Cookie;
import org.openqa.selenium.devtools.v127.storage.Storage;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class ManageCookiesCDPChromeTest {

    WebDriver driver;

    DevTools devTools;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        //pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(10).toMillis());

        devTools.close();
        driver.quit();
    }

    @Test
    public void testManageCookies() {
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty()));
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/cookies.html");

        // Read cookies
        List<Cookie> cookies = devTools
                .send(Storage.getCookies(Optional.empty()));
        cookies.forEach(cookie -> log.info("{}={}", cookie.getName(),
                cookie.getValue()));
        List<String> cookieName = cookies.stream()
                .map(cookie -> cookie.getName()).sorted()
                .collect(Collectors.toList());
        Set<org.openqa.selenium.Cookie> seleniumCookie = driver.manage()
                .getCookies();
        List<String> selCookieName = seleniumCookie.stream()
                .map(selCookie -> selCookie.getName()).sorted()
                .collect(Collectors.toList());
        assertThat(cookieName).isEqualTo(selCookieName);

        // Clear cookies
        devTools.send(Network.clearBrowserCookies());
        List<Cookie> cookiesAfterClearing = devTools
                .send(Storage.getCookies(Optional.empty()));
        assertThat(cookiesAfterClearing).isEmpty();

        driver.findElement(By.id("refresh-cookies")).click();
    }
}
