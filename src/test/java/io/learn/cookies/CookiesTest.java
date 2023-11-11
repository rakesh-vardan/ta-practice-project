package io.learn.cookies;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class CookiesTest extends BaseTest {

    @Test
    void testReadCookies() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/cookies.html");

        WebDriver.Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        assertThat(cookies).hasSize(2);

        Cookie username = options.getCookieNamed("username");
        assertThat(username.getValue()).isEqualTo("John Doe");
        assertThat(username.getPath()).isEqualTo("/");

        driver.findElement(By.id("refresh-cookies")).click();
    }

    @Test
    void testAddCookies() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/cookies.html");

        WebDriver.Options options = driver.manage();
        Cookie newCookie = new Cookie("new-cookie-key", "new-cookie-value");
        options.addCookie(newCookie);
        String readValue = options.getCookieNamed(newCookie.getName())
                .getValue();
        assertThat(newCookie.getValue()).isEqualTo(readValue);

        driver.findElement(By.id("refresh-cookies")).click();
    }

    @Test
    void testEditCookie() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/cookies.html");

        WebDriver.Options options = driver.manage();
        Cookie username = options.getCookieNamed("username");
        Cookie editedCookie = new Cookie(username.getName(), "new-value");
        options.addCookie(editedCookie);

        Cookie readCookie = options.getCookieNamed(username.getName());
        assertThat(editedCookie).isEqualTo(readCookie);

        driver.findElement(By.id("refresh-cookies")).click();
    }

    @Test
    void testDeleteCookies() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/cookies.html");

        WebDriver.Options options = driver.manage();
        Set<Cookie> cookies = options.getCookies();
        Cookie username = options.getCookieNamed("username");
        options.deleteCookie(username);

        assertThat(options.getCookies()).hasSize(cookies.size() - 1);

        driver.findElement(By.id("refresh-cookies")).click();
    }

}
