package demo;

import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Durations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.w3c.dom.html.HTMLBodyElement;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;

public class TestCases {
    RemoteWebDriver driver;
    Wrappers wrappers;

    @BeforeTest
    public void startBrowser() {
        try {
            System.out.println("Constructor: TestCases");

            // Launch Browser using Zalenium
            final DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome");
            driver = new RemoteWebDriver(new URL("http://localhost:8082/wd/hub"), capabilities);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            wrappers = new Wrappers(driver); // Initialize Wrappers class with driver

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testCase01() throws InterruptedException {
        System.out.println("Start testing testCase01 ");
        wrappers.openUrl("https://forms.gle/wjPkzeSEk1CM7KgGA");

        boolean status = wrappers.verifyUrlContains("forms");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@role=\"list\"]/div[@role=\"listitem\"]")));

        WebElement inputName = wrappers.getElementByXpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[1]/div/div/div[2]/div/div/div/div/input");
        wrappers.click(inputName);
        wrappers.clearAndSendKeys(inputName, "Crio Learner");


        // Thread.sleep(2000);

        WebElement textareaDescription = wrappers.getElementByXpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[2]/div/div/div[2]/div/div/div[2]/textarea");
        wrappers.click(textareaDescription);

        long epoch = System.currentTimeMillis() / 1000;
        wrappers.clearAndSendKeys(textareaDescription, "I want to be the best QA Engineer " + epoch);
        

        // Thread.sleep(2000);

        WebElement clickElement = wrappers.getElementByXpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[3]/div/div/div[2]/div/div/span/div/div[4]/label/div/div[1]");

        wrappers.click(clickElement);

        wrappers.clickByXpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[4]/div/div/div[2]/div[@role=\"list\"]/div[1]");
        wrappers.clickByXpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[4]/div/div/div[2]/div[@role=\"list\"]/div[2]");
        wrappers.clickByXpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[4]/div/div/div[2]/div[@role=\"list\"]/div[4]");

        // Thread.sleep(2000);

        wrappers.clickByXpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[9]/div/div/div[2]/div");
        // Thread.sleep(2000);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@data-value=\"Mr\"])[2]")));

        wrappers.clickByXpath("(//div[@data-value=\"Mr\"])[2]");

        // Thread.sleep(2000);

        LocalDate date = LocalDate.now().minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);
        // System.out.println("formattedDate " + formattedDate);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='date']")));

        WebElement dateField = wrappers.getElementByXpath("//input[@type='date']");
        dateField.sendKeys(formattedDate);

        // Thread.sleep(10000);

        WebElement hourField = wrappers.getElementByXpath("//input[@aria-label='Hour']");
        WebElement minuteField = wrappers.getElementByXpath("//input[@aria-label='Minute']");

        wrappers.sendKeys(hourField, "07");
        wrappers.sendKeys(minuteField, "30");

        wrappers.setDate(driver, dateField, formattedDate);

        wrappers.clickByXpath("//div[@role=\"button\" and @aria-label=\"Submit\"]");

        // wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//div[@role='alert']/span"), "This is a required question"));

        wait.until(ExpectedConditions.urlContains("formResponse"));

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[3]")));

        String successMessage = wrappers.getTextByXpath("/html/body/div[1]/div[2]/div[1]/div/div[3]");
        System.out.println(successMessage);

        System.out.println("End testing testCase01 ");
    }

    @AfterTest
    public void endTest() {
        driver.close();
        driver.quit();
    }
}
