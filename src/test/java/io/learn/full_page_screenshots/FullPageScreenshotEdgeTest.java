package io.learn.full_page_screenshots;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.dom.model.Rect;
import org.openqa.selenium.devtools.v119.page.Page;
import org.openqa.selenium.devtools.v119.page.model.Viewport;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Base64;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class FullPageScreenshotEdgeTest {

    DevTools devTools;
    WebDriver driver;

    @BeforeMethod
    public void setup() {
        driver = new EdgeDriver();
        devTools = ((EdgeDriver) driver).getDevTools();
        devTools.createSession();
    }

    @AfterMethod
    public void teardown() {
        devTools.close();
        driver.quit();
    }

    @Test
    public void testFullPageScreenshotEdge() throws IOException {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/long-page.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(
                By.className("container"), By.tagName("p")));

        Page.GetLayoutMetricsResponse metrics = devTools
                .send(Page.getLayoutMetrics());
        Rect contentSize = metrics.getContentSize();
        String screenshotBase64 = devTools
                .send(Page.captureScreenshot(Optional.empty(), Optional.empty(),
                        Optional.of(new Viewport(0, 0, contentSize.getWidth(),
                                contentSize.getHeight(), 1)),
                        Optional.empty(), Optional.of(true),
                        Optional.of(false)));
        Path destination = Paths.get("fullpage-screenshot-edge.png");
        Files.write(destination, Base64.getDecoder().decode(screenshotBase64));

        assertThat(destination).exists();
    }
}
