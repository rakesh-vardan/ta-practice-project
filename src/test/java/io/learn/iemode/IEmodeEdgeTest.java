package io.learn.iemode;

import java.nio.file.Path;
import java.util.Optional;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.apache.commons.lang3.SystemUtils.IS_OS_WINDOWS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assumptions.assumeThat;

@Log4j2
public class IEmodeEdgeTest {

    WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        assumeThat(IS_OS_WINDOWS).isTrue(); // IE mode is only supported on Win
        WebDriverManager.iedriver().setup();
    }

    @BeforeMethod
    public void setup() {
        Optional<Path> browserPath = WebDriverManager.edgedriver()
                .getBrowserPath();
        assumeThat(browserPath).isPresent();

        InternetExplorerOptions options = new InternetExplorerOptions();
        options.attachToEdgeChrome();
        options.withEdgeExecutablePath(browserPath.get().toString());

        driver = new InternetExplorerDriver(options);
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testIEmodeEdge() {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/");
        assertThat(driver.getTitle()).contains("Selenium WebDriver");
    }
}
