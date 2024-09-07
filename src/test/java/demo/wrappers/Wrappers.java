package demo.wrappers;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wrappers {
    RemoteWebDriver driver;

    public Wrappers(RemoteWebDriver driver) {
        this.driver = driver;
    }

    // Open URL
    public void openUrl(String url) {
        driver.get(url);
    }

    // Verify URL contains a specific text
    public boolean verifyUrlContains(String text) {
        return driver.getCurrentUrl().contains(text);
    }

    // Get Element by XPath
    public WebElement getElementByXpath(String xpath) {
        return driver.findElement(By.xpath(xpath));
    }

    // Click Element
    public void click(WebElement element) {
        element.click();
    }

    // Click Element by XPath
    public void clickByXpath(String xpath) {
        WebElement element = getElementByXpath(xpath);
        click(element);
    }

    // Clear and Send Keys
    public void clearAndSendKeys(WebElement element, String text) {
        element.clear();
        element.sendKeys(text);
    }

    // Send Keys
    public void sendKeys(WebElement element, String text) {
        element.sendKeys(text);
    }

    // Get Text from Element by XPath
    public String getTextByXpath(String xpath) {
        return getElementByXpath(xpath).getText();
    }

    // // Set Date using JavaScript
    // public void setDate(WebElement element, String date) {
    //     JavascriptExecutor js = (JavascriptExecutor) driver;
    //     js.executeScript("arguments[0].value = '';", element); // Clear the field
    //     js.executeScript("arguments[0].value = arguments[1];", element, date); // Set the value
    //     js.executeScript("arguments[0].dispatchEvent(new Event('input'));"); // Trigger input event
    //     js.executeScript("arguments[0].dispatchEvent(new Event('change'));"); // Trigger change event
    // }

    public void setDate(RemoteWebDriver driver, WebElement element, String date) {
        try {
            // Ensure element is visible and interactable
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
    
            // Set the date using JavaScript
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));", element, date);
        } catch (Exception e) {
            System.out.println("Error setting date: " + e.getMessage());
        }
    }
    
    
}
