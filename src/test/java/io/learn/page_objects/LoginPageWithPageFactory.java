package io.learn.page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageWithPageFactory {

    @FindBy(id = "username")
    WebElement usernameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(css = "button")
    WebElement submitButton;

    @FindBy(id = "success")
    WebElement successBox;

    @FindBy(id = "invalid")
    WebElement failureMessage;


    public LoginPageWithPageFactory(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterUserName(String userName) {
        usernameInput.sendKeys(userName);
    }

    public void enterPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        submitButton.click();
    }

    public void login(String userName, String password) {
        enterUserName(userName);
        enterPassword(password);
        clickLogin();
    }

    public boolean isLoginSuccessful() {
        return successBox.isDisplayed();
    }

    public boolean isLoginFailed() {
        return failureMessage.isDisplayed();
    }
}
