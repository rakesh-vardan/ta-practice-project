package io.learn.notifications;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class NotificationEdgeTest {

    WebDriver driver;

    @BeforeClass
    public void setup() {
        EdgeOptions options = new EdgeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_setting_values.notification", 1);
        options.setExperimentalOption("prefs", prefs);

        driver = new EdgeDriver(options);
    }

    @Test
    public void testNotification() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/notifications.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        String script = String.join("\n",
                "const callback = arguments[arguments.length - 1];",
                "const OldNotify = window.Notification;",
                "function newNotification(title, options) {",
                "    callback(title);",
                "    return new OldNotify(title, options);",
                "}",
                "newNotification.requestPermission = OldNotify.requestPermission.bind(OldNotify);",
                "Object.defineProperty(newNotification, 'permission', {",
                "    get: function() {",
                "        return OldNotify.permission;",
                "    }",
                "});",
                "window.Notification = newNotification;",
                "document.getElementById('notify-me').click();");
        log.debug("Executing the following script asynchronously:\n{}", script);

        Object notificationTitle = js.executeAsyncScript(script);
        assertThat(notificationTitle).isEqualTo("This is a notification");
    }

    @AfterClass
    public void teardown() throws InterruptedException {
        //pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(10).toMillis());

        driver.quit();
    }
}
