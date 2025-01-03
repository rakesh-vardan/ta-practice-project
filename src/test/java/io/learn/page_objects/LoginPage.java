package io.learn.page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;

    By usernameInput = By.id("username");
    By passwordInput = By.id("password");
    By submitButton = By.cssSelector("button");
    By successBox = By.id("success");
    By failureMessage = By.id("invalid");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUserName(String userName) {
        driver.findElement(usernameInput).sendKeys(userName);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(submitButton).click();
    }

    public void login(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginSuccessful() {
        return driver.findElement(successBox).isDisplayed();
    }

    public boolean isLoginFailed() {
        return driver.findElement(failureMessage).isDisplayed();
    }
}
