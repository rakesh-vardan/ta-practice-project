package io.learn.window;

import io.learn.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class WindowTests extends BaseTest {

    @Test
    void testWindow() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        WebDriver.Window window = driver.manage().window();

        Point initialPosition = window.getPosition();
        Dimension initialSize = window.getSize();
        log.info("Initial window: position {} -- size {}", initialPosition,
                initialSize);

        window.maximize();

        Point maximizedPosition = window.getPosition();
        Dimension maximizedSize = window.getSize();
        log.info("Maximized window: position {} -- size {}", maximizedPosition,
                maximizedSize);

        assertThat(initialPosition).isNotEqualTo(maximizedPosition);
        assertThat(initialSize).isNotEqualTo(maximizedSize);
    }
}
