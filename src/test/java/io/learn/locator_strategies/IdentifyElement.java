package io.learn.locator_strategies;

import io.learn.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.openqa.selenium.support.pagefactory.ByAll;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class IdentifyElement extends BaseTest {

    public static final String URL = "https://bonigarcia.dev/selenium-webdriver-java/web-form.html";

    @Test
    public void testByTagName() {
        driver.get(URL);

        WebElement textarea = driver.findElement(By.tagName("textarea"));
        assertThat(textarea.getDomAttribute("rows")).isEqualTo("3");
    }

    @Test
    void testByHtmlAttributes() {
        driver.get(URL);

        // By name
        WebElement textByName = driver.findElement(By.name("my-text"));
        assertThat(textByName.isEnabled()).isTrue();

        // By id
        WebElement textById = driver.findElement(By.id("my-text-id"));
        assertThat(textById.getAttribute("type")).isEqualTo("text");
        assertThat(textById.getDomAttribute("type")).isEqualTo("text");
        assertThat(textById.getDomProperty("type")).isEqualTo("text");

        assertThat(textById.getAttribute("myprop")).isEqualTo("myvalue");
        assertThat(textById.getDomAttribute("myprop")).isEqualTo("myvalue");
        assertThat(textById.getDomProperty("myprop")).isNull();

        // By class name
        List<WebElement> byClassName = driver
                .findElements(By.className("form-control"));
        assertThat(byClassName.size()).isPositive();
        assertThat(byClassName.get(0).getAttribute("name")).isEqualTo("my-text");
    }

    @Test
    void testByLinkText() {
        driver.get(URL);

        WebElement linkByText = driver
                .findElement(By.linkText("Return to index"));
        assertThat(linkByText.getTagName()).isEqualTo("a");
        assertThat(linkByText.getCssValue("cursor")).isEqualTo("pointer");

        WebElement linkByPartialText = driver
                .findElement(By.partialLinkText("index"));
        assertThat(linkByPartialText.getLocation())
                .isEqualTo(linkByText.getLocation());
        assertThat(linkByPartialText.getRect()).isEqualTo(linkByText.getRect());
    }

    @Test
    void testByCssSelectorBasic() {
        driver.get(URL);

        WebElement hidden = driver
                .findElement(By.cssSelector("input[type=hidden]"));
        assertThat(hidden.isDisplayed()).isFalse();
    }

    @Test
    void testByCssSelectorAdvanced() {
        driver.get(URL);

        WebElement checkbox1 = driver
                .findElement(By.cssSelector("[type=checkbox]:checked"));
        assertThat(checkbox1.getAttribute("id")).isEqualTo("my-check-1");
        assertThat(checkbox1.isSelected()).isTrue();

        WebElement checkbox2 = driver
                .findElement(By.cssSelector("[type=checkbox]:not(:checked)"));
        assertThat(checkbox2.getAttribute("id")).isEqualTo("my-check-2");
        assertThat(checkbox2.isSelected()).isFalse();
    }

    @Test
    void testByXPathBasic() {
        driver.get(URL);

        WebElement hidden = driver
                .findElement(By.xpath("//input[@type='hidden']"));
        assertThat(hidden.isDisplayed()).isFalse();
    }

    @Test
    void testByXPathAdvanced() {
        driver.get(URL);

        WebElement radio1 = driver
                .findElement(By.xpath("//*[@type='radio' and @checked]"));
        assertThat(radio1.getAttribute("id")).isEqualTo("my-radio-1");
        assertThat(radio1.isSelected()).isTrue();

        WebElement radio2 = driver
                .findElement(By.xpath("//*[@type='radio' and not(@checked)]"));
        assertThat(radio2.getAttribute("id")).isEqualTo("my-radio-2");
        assertThat(radio2.isSelected()).isFalse();
    }

    @Test
    void testByIdOrName() {
        driver.get(URL);

        WebElement fileElement = driver.findElement(new ByIdOrName("my-file"));
        assertThat(fileElement.getAttribute("id")).isBlank();
        assertThat(fileElement.getAttribute("name")).isNotBlank();
    }

    @Test
    void testByChained() {
        driver.get(URL);

        List<WebElement> rowsInForm = driver.findElements(
                new ByChained(By.tagName("form"), By.className("row")));
        assertThat(rowsInForm.size()).isEqualTo(1);
    }

    @Test
    void testByAll() {
        driver.get(URL);

        List<WebElement> rowsInForm = driver.findElements(
                new ByAll(By.tagName("form"), By.className("row")));
        assertThat(rowsInForm.size()).isEqualTo(5);
    }

    @Test
    void testRelativeLocators() {
        driver.get(URL);

        WebElement link = driver.findElement(By.linkText("Return to index"));
        RelativeLocator.RelativeBy relativeBy = RelativeLocator.with(By.tagName("input"));
        WebElement readOnly = driver.findElement(relativeBy.above(link));
        assertThat(readOnly.getAttribute("name")).isEqualTo("my-readonly");
    }

    @Test
    void testDatePicker() {
        driver.get(URL);

        // Get the current date from the system clock
        LocalDate today = LocalDate.now();
        int currentYear = today.getYear();
        int currentDay = today.getDayOfMonth();

        // Click on the date picker to open the calendar
        WebElement datePicker = driver.findElement(By.name("my-date"));
        datePicker.click();

        // Click on the current month by searching by text
        WebElement monthElement = driver.findElement(By.xpath(
                String.format("//th[contains(text(),'%d')]", currentYear)));
        monthElement.click();

        // Click on the left arrow using relative locators
        WebElement arrowLeft = driver.findElement(
                RelativeLocator.with(By.tagName("th")).toRightOf(monthElement));
        arrowLeft.click();

        // Click on the current month of that year
        WebElement monthPastYear = driver.findElement(RelativeLocator
                .with(By.cssSelector("span[class$=focused]")).below(arrowLeft));
        monthPastYear.click();

        // Click on the present day in that month
        WebElement dayElement = driver.findElement(By.xpath(String.format(
                "//td[@class='day' and contains(text(),'%d')]", currentDay)));
        dayElement.click();

        // Get the final date on the input text
        String oneYearBack = datePicker.getAttribute("value");
        log.info("Final date in date picker: {}", oneYearBack);

        // Assert that the expected date is equal to the one selected in the
        // date picker
        LocalDate previousYear = today.minusYears(1);
        DateTimeFormatter dateFormat = DateTimeFormatter
                .ofPattern("MM/dd/yyyy");
        String expectedDate = previousYear.format(dateFormat);
        log.info("Expected date: {}", expectedDate);

        assertThat(oneYearBack).isEqualTo(expectedDate);
    }

    @Test
    void testSendKeys() {
        driver.get(URL);

        WebElement inputText = driver.findElement(By.name("my-text"));
        String textValue = "Hello World!";
        inputText.sendKeys(textValue);
        assertThat(inputText.getAttribute("value")).isEqualTo(textValue);

        inputText.clear();
        assertThat(inputText.getAttribute("value")).isEmpty();
    }

    @Test
    void testUploadFile() throws IOException {
        driver.get(URL);

        WebElement inputFile = driver.findElement(By.name("my-file"));

        Path tempFile = Files.createTempFile("tempfiles", ".tmp");
        String filename = tempFile.toAbsolutePath().toString();
        log.debug("Using temporal file {} in file uploading", filename);
        inputFile.sendKeys(filename);

        driver.findElement(By.tagName("form")).submit();
        assertThat(driver.getCurrentUrl()).isNotEqualTo(URL);
    }

    @Test
    void testSlider() {
        driver.get(URL);

        WebElement slider = driver.findElement(By.name("my-range"));
        String initValue = slider.getAttribute("value");
        log.debug("The initial value of the slider is {}", initValue);

        for (int i = 0; i < 5; i++) {
            slider.sendKeys(Keys.ARROW_RIGHT);
        }

        String endValue = slider.getAttribute("value");
        log.debug("The final value of the slider is {}", endValue);
        assertThat(initValue).isNotEqualTo(endValue);
    }
}
