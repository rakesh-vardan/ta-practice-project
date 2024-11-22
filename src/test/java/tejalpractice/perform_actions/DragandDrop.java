package tejalpractice.perform_actions;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DragandDrop {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void testDragDrop()
    {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/drag-and-drop.html");
        Actions action = new Actions(driver);

        // ???? how will i decide offset value

        WebElement elementDraggable = driver.findElement(By.id("draggable"));
        int offset = 100;
         Point initLocation = elementDraggable.getLocation();
       action.dragAndDropBy(elementDraggable,offset, yOffset:0)
        .dragAndDropBy(elementDraggable,xOffset: 0,offset)
        .dragAndDropBy(elementDraggable,offset,yOffset: 0)
        .dragAndDropBy(elementDraggable,xOffset: 0,-offset)
        .dragAndDropBy(elementDraggable,-offset,yOffset: 0).build().perform();
       assertThat(initLocation).isEqualTo(elementDraggable.getLocation());
       // ???? OR
       WebElement target = driver.findElement(By.id("target"));
        action.dragAndDrop(elementDraggable,target).build().perform();

    }

    @AfterClass
    public void tearDown()
    {
        driver.quit();
    }
}
