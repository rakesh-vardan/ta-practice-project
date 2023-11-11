package io.learn.timeouts;

import io.learn.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScriptTimeoutException;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TimeOutTests extends BaseTest {

    @Test
    void testPageLoadTimeout() {
        driver.manage().timeouts().pageLoadTimeout(Duration.ofMillis(1));

        assertThatThrownBy(() -> driver
                .get("https://bonigarcia.dev/selenium-webdriver-java/"))
                .isInstanceOf(TimeoutException.class);
    }

    @Test
    void testScriptTimeout() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(3));

        assertThatThrownBy(() -> {
            long waitMillis = Duration.ofSeconds(5).toMillis();
            String script = "const callback = arguments[arguments.length - 1];"
                    + "window.setTimeout(callback, " + waitMillis + ");";
            js.executeAsyncScript(script);
        }).isInstanceOf(ScriptTimeoutException.class);
    }
}
