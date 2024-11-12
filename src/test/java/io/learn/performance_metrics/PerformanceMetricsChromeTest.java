package io.learn.performance_metrics;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v127.performance.Performance;
import org.openqa.selenium.devtools.v127.performance.model.Metric;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class PerformanceMetricsChromeTest {

    WebDriver driver;

    DevTools devTools;

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();
    }

    @AfterMethod
    public void teardown() throws InterruptedException {
        //pause for manual browser inspection
        Thread.sleep(Duration.ofSeconds(10).toMillis());

        devTools.close();
        driver.quit();
    }

    @Test
    public void testPerformanceMetrics() {
        devTools.send(Performance.enable(Optional.empty()));
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");

        List<Metric> metrics = devTools.send(Performance.getMetrics());
        assertThat(metrics).isNotEmpty();

        for(Metric metric: metrics)
        {
            log.info(metric.getName());
            log.info(metric.getValue());
        }
    }
}
