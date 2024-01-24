package io.learn.locator_strategies;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IdentifyElement3 extends BaseTest {

    String URL1 = "https://bonigarcia.dev/selenium-webdriver-java/login-form.html";
    String URL2 = "https://demo.guru99.com/V4/";

    @Test
    public void testRelativeLocatorAbove() {
        driver.get(URL1);
        RelativeLocator.RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        WebElement userName = driver.findElement(relativeBy.above(By.id("password")));
        userName.sendKeys("User");
        assertThat(userName.getAttribute("name")).isEqualTo("username");
    }

    @Test
    public void testRelativeLocatorBelow() {
        driver.get(URL1);
        RelativeLocator.RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        WebElement password = driver.findElement(relativeBy.below(By.id("username")));
        password.sendKeys("pass");
        assertThat(password.getAttribute("name")).isEqualTo("password");
    }

    @Test
    public void testRelativeLocatorLeft() {
        driver.get(URL2);
        RelativeLocator.RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        WebElement loginButton = driver.findElement(relativeBy.toLeftOf(By.name("btnReset")));
        assertThat(loginButton.getAttribute("value")).isEqualTo("LOGIN");
        assertThat(loginButton.isEnabled()).isTrue();
    }

    @Test
    public void testRelativeLocatorRight() {
        driver.get(URL2);
        RelativeLocator.RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        WebElement resetButton = driver.findElement(relativeBy.toRightOf(By.name("btnLogin")));
        assertThat(resetButton.getAttribute("value")).isEqualTo("RESET");
        assertThat(resetButton.isEnabled()).isTrue();
    }

    @Test
    public void testRelativeLocatorNearBy() {
        driver.get(URL1);
        RelativeLocator.RelativeBy relativeBy = RelativeLocator.with(By.tagName("label"));
        WebElement label = driver.findElement(relativeBy.near(By.id("username")));
        assertThat(label.getText()).isEqualTo("Login");
    }

    @Test
    public void testRelativeLocatorChaining() {
        driver.get(URL2);
        RelativeLocator.RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        WebElement loginButton = driver.findElement(relativeBy
                .below(By.name("password"))
                .toLeftOf(By.name("btnReset")));
        assertThat(loginButton.getAttribute("value")).isEqualTo("LOGIN");
        assertThat(loginButton.isEnabled()).isTrue();
    }
}
