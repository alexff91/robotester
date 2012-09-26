package robotester.selenium.mds;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.LinkedList;
import java.util.List;


public class TestRobot {
    @Test
    public void getElements() {
        try {
            // Create a new instance of the html unit driver
            // Notice that the remainder of the code relies on the interface,
            // not the implementation.
            WebDriver driver = new HtmlUnitDriver();
            driver.get("http://www.yandex.ru");

            WebElement searchBox = driver.findElement(By.name("text"));
            searchBox.sendKeys("стажировка");
            searchBox.sendKeys(Keys.RETURN);

            List<List<WebElement>> elements = findAllElements(driver);
            for (List<WebElement> e : elements) {
                System.out.println("<--------------------" + e.toString() + "---------------------->");
                for (WebElement elem : e) {
                    System.out.println(elem.getTagName()+ elem.getText());
                }
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
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

    private List<List<WebElement>> findAllElements(WebDriver driver) {
        List<WebElement> allLinks = driver.findElements(By.xpath("//*"));

        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        List<WebElement> links = driver.findElements(By.tagName("a"));
        List<WebElement> allElems = driver.findElements(By.xpath("//DIV[@id='someId']//A[@class='someClass']"));
        List<List<WebElement>> l = new LinkedList<List<WebElement>>();
        l.add(allElems);
        l.add(allLinks);
        l.add(links);
        l.add(inputs);
        return l;
    }
}
