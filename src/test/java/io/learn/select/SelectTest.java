package io.learn.select;

import io.learn.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectTest extends BaseTest {

    @Test
    void test() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        Select select = new Select(driver.findElement(By.name("my-select")));
        String optionLabel = "Three";
        select.selectByVisibleText(optionLabel);

        assertThat(select.getFirstSelectedOption().getText())
                .isEqualTo(optionLabel);
    }

    @Test
    void testDatalist() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-form.html");

        WebElement datalist = driver.findElement(By.name("my-datalist"));
        datalist.click();

        WebElement option = driver
                .findElement(By.xpath("//datalist/option[2]"));
        String optionValue = option.getAttribute("value");
        datalist.sendKeys(optionValue);

        assertThat(optionValue).isEqualTo("New York");
    }
}
