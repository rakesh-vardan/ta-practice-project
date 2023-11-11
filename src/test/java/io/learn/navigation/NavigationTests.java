package io.learn.navigation;

import io.learn.BaseTest;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NavigationTests extends BaseTest {

    @Test
    void testHistory() {
        String baseUrl = "https://bonigarcia.dev/selenium-webdriver-java/";
        String firstPage = baseUrl + "navigation1.html";
        String secondPage = baseUrl + "navigation2.html";
        String thirdPage = baseUrl + "navigation3.html";

        driver.get(firstPage);

        driver.navigate().to(secondPage);
        driver.navigate().to(thirdPage);
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        assertThat(driver.getCurrentUrl()).isEqualTo(thirdPage);
    }
}
