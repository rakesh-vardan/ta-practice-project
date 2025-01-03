package io.learn.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginPageTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/login-form.html");
    }

    @Test
    public void testLoginWithNormalApproach() {
        driver.findElement(By.id("username")).sendKeys("user");
        driver.findElement(By.id("password")).sendKeys("user");
        driver.findElement(By.cssSelector("button")).click();
        assertThat(driver.findElement(By.id("success")).isDisplayed()).isTrue();
    }

    @Test
    public void testValidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("user", "user");
        assertThat(loginPage.isLoginSuccessful()).isTrue();
    }

    @Test
    public void testInvalidLogin() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("user-invalid", "user123");
        assertThat(loginPage.isLoginFailed()).isTrue();
    }

    @Test
    public void testValidLoginWithPageFactory() {
        LoginPageWithPageFactory loginPage = new LoginPageWithPageFactory(driver);
        loginPage.login("user", "user");
        assertThat(loginPage.isLoginSuccessful()).isTrue();
    }

    @Test
    public void testInvalidLoginWithPageFactory() {
        LoginPageWithPageFactory loginPage = new LoginPageWithPageFactory(driver);
        loginPage.login("user-invalid", "user123");
        assertThat(loginPage.isLoginFailed()).isTrue();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
