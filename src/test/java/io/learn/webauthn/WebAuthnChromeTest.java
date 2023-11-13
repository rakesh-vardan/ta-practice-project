package io.learn.webauthn;

import java.time.Duration;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.virtualauthenticator.HasVirtualAuthenticator;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticator;
import org.openqa.selenium.virtualauthenticator.VirtualAuthenticatorOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class WebAuthnChromeTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        //pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(10).toMillis());
        driver.quit();
    }

    @Test
    public void testWebAuthn() {
        driver.get("https://webauthn.io/");
        HasVirtualAuthenticator virtualAuth = (HasVirtualAuthenticator) driver;
        VirtualAuthenticator authenticator = virtualAuth
                .addVirtualAuthenticator(new VirtualAuthenticatorOptions());

        String randomId = UUID.randomUUID().toString();
        driver.findElement(By.id("input-email")).sendKeys(randomId);
        driver.findElement(By.id("register-button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.className("alert"), "Success! Now try to authenticate..."));

        driver.findElement(By.id("login-button")).click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.className("main-content"), "You're logged in!"));

        virtualAuth.removeVirtualAuthenticator(authenticator);
    }
}
