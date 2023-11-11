package io.learn.window_handling;

import io.learn.BaseTest;
import org.openqa.selenium.WindowType;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WindowHandlingTests extends BaseTest {

    @Test
    void testNewTab() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String initHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(driver.getWindowHandles().size()).isEqualTo(2);

        driver.switchTo().window(initHandle);
        driver.close();
        assertThat(driver.getWindowHandles().size()).isEqualTo(1);
    }

    @Test
    void testNewWindow() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        String initHandle = driver.getWindowHandle();

        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");
        assertThat(driver.getWindowHandles().size()).isEqualTo(2);

        driver.switchTo().window(initHandle);
        driver.close();
        assertThat(driver.getWindowHandles().size()).isEqualTo(1);
    }
}
