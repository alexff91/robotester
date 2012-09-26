package robotester.selenium.mds;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class TestRobot {
    @Test
    public void getElements() {
        WebDriver driver = new FirefoxDriver();
        Wait wait = new WebDriverWait(driver,500);

        driver.get("http://www.google.com");

        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("стажировка");
        searchBox.sendKeys(Keys.RETURN);
        WebElement link = (WebElement) wait.until(textIsPresent("centercareer.ru"));
        link.click();

    }

    private ExpectedCondition<WebElement> textIsPresent(final String text) {
        return new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver) {
                List<WebElement> allLinks = driver.findElements(By.tagName("a"));
                for (WebElement link : allLinks) {
                    if (link.getText().contains(text)) {
                        return link;
                    }

                }
                return null;

            }
        };
    }
}
