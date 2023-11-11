package shadow_dom;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ShadowDomTests extends BaseTest {

    @Test
    void testShadowDom() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/shadow-dom.html");

        WebElement content = driver.findElement(By.id("content"));
        SearchContext shadowRoot = content.getShadowRoot();
        WebElement textElement = shadowRoot.findElement(By.cssSelector("p"));
        assertThat(textElement.getText()).contains("Hello Shadow DOM");
    }
}
