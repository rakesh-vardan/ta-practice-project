package io.learn.local_storage;

import io.learn.BaseTest;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class StorageTest extends BaseTest {

    @Test
    void testWebStorage() {
        driver.get(
                "https://bonigarcia.dev/selenium-webdriver-java/web-storage.html");
        WebStorage webStorage = (WebStorage) driver;

        LocalStorage localStorage = webStorage.getLocalStorage();
        log.info("Local storage elements: {}", localStorage.size());

        SessionStorage sessionStorage = webStorage.getSessionStorage();
        sessionStorage.keySet()
                .forEach(key -> log.info("Session storage: {}={}", key,
                        sessionStorage.getItem(key)));
        assertThat(sessionStorage.size()).isEqualTo(2);

        sessionStorage.setItem("new element", "new value");
        assertThat(sessionStorage.size()).isEqualTo(3);

        driver.findElement(By.id("display-session")).click();
    }
}
