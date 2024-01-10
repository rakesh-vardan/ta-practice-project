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
public class IdentifyElement2 extends BaseTest {

    public static final String URL = "file:///Users/Rakesh_Budugu/locator-file2.html";

    @Test
    public void testMe() throws InterruptedException {
        driver.get(URL);

        //using id
        boolean firstName = driver.findElement(By.id("firstName")).isDisplayed();
        System.out.println(firstName);
        boolean submitBtn = driver.findElement(By.id("submitBtn")).isDisplayed();
        System.out.println(submitBtn);

        //using name
        boolean firstName1 = driver.findElement(By.name("firstName")).isDisplayed();
        System.out.println(firstName1);
        boolean submitBtn1 = driver.findElement(By.name("submitBtn")).isDisplayed();
        System.out.println(submitBtn1);

        //using className
        boolean className1 = driver.findElement(By.className("main")).isDisplayed();
        System.out.println(className1);
        boolean className2 = driver.findElement(By.className("btn")).isDisplayed();
        System.out.println(className2);

        //using tag name
        boolean tagName1 = driver.findElement(By.tagName("input")).isDisplayed();
        System.out.println(tagName1);

        //using link text
        boolean linkText1 = driver.findElement(By.linkText("Visit Example.com")).isDisplayed();
        System.out.println(linkText1);

        //using partial link text
        boolean plinkText1 = driver.findElement(By.partialLinkText("Visit")).isDisplayed();
        System.out.println(plinkText1);

        //using xpath
        boolean xpath1 = driver.findElement(By.xpath("//*[@id='firstName']")).isDisplayed();
        System.out.println(xpath1);

        boolean xpath2 = driver.findElement(By.xpath("//button[contains(@class, 'submit')]")).isDisplayed();
        System.out.println(xpath2);

        //using css
        boolean css1 = driver.findElement(By.cssSelector("input#firstName")).isDisplayed();
        System.out.println(css1);

        boolean css2 = driver.findElement(By.cssSelector("div.main > button.submit")).isDisplayed();
        System.out.println(css2);

        Thread.sleep(10000L);
    }

}
