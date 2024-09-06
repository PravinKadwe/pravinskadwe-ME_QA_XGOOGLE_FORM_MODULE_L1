package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

    @Test
    public void testCase01() throws InterruptedException{
        System.out.println("Start testing testCase01 ");
        driver.get("https://forms.gle/wjPkzeSEk1CM7KgGA");

        boolean status = driver.getCurrentUrl().contains("forms");

        // if(status){System.out.println("Navigated to the google form");}else{System.out.println("Failed to Navigated to the google form");}

        Thread.sleep(5000);

        WebElement inputName = driver.findElement(By.xpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[1]/div/div/div[2]/div/div/div/div/input"));

        inputName.click();

        inputName.clear();

        inputName.sendKeys("Crio Learner");

        Thread.sleep(2000);

        status = inputName.getText().contains("Crio Learner");

        // if(status){System.out.println("Typed name into input box");}else{System.out.println("Failed to Type name into input box");}

        WebElement textareaDiscription = driver.findElement(By.xpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[2]/div/div/div[2]/div/div/div[2]/textarea"));

        textareaDiscription.click();

        textareaDiscription.clear();

        long epoch = System.currentTimeMillis()/1000;

        textareaDiscription.sendKeys("I want to be the best QA Engineer "+epoch);

        Thread.sleep(2000);

        status = textareaDiscription.getText().contains("I want to be the best QA Engineer");

        // if(status){System.out.println("Typed phrase into input box");}else{System.out.println("Failed to Type phrase into input box");}

        WebElement ClickElement = driver.findElement(By.xpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[3]/div/div/div[2]/div/div/span/div/div[4]/label/div/div[1]"));

        ClickElement.click();

        Thread.sleep(2000);

        status = ClickElement.isSelected();
        
        // if(status){System.out.println("Clicked on Experience box");}else{System.out.println("Failed to Click on Experience box");}

        driver.findElement(By.xpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[4]/div/div/div[2]/div[@role=\"list\"]/div[1]")).click();

        driver.findElement(By.xpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[4]/div/div/div[2]/div[@role=\"list\"]/div[2]")).click();

        driver.findElement(By.xpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[4]/div/div/div[2]/div[@role=\"list\"]/div[4]")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("(//div[@role=\"list\"]/div[@role=\"listitem\"])[9]/div/div/div[2]/div")).click();

        Thread.sleep(2000);

        driver.findElement(By.xpath("(//div[@data-value=\"Mr\"])[2]")).click();

        Thread.sleep(2000);

        // Calculate the date 7 days ago
        LocalDate date = LocalDate.now().minusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = date.format(formatter);

        System.out.println("formattedDate "+formattedDate);

        // Find the date input field
        WebElement dateField = driver.findElement(By.xpath("//input[@type='date']"));

        // Use JavaScript to set the value of the date field
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = '';", dateField); // Clear the field
        js.executeScript("arguments[0].value = arguments[1];", dateField, formattedDate); // Set the value
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", dateField); // Trigger input event
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", dateField); // Trigger change event
        

        Thread.sleep(2000);
        
        WebElement hourField = driver.findElement(By.xpath("//input[@aria-label='Hour']"));
        WebElement minuteField = driver.findElement(By.xpath("//input[@aria-label='Minute']"));

        hourField.sendKeys("07");
        minuteField.sendKeys("30");


        driver.findElement(By.xpath("//div[@role=\"button\" and @aria-label=\"Submit\"]")).click();


        Thread.sleep(10000);

        driver.findElement(By.xpath("//div[@role=\"button\" and @aria-label=\"Submit\"]")).click();

        String SuccessMessage = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[1]/div/div[3]")).getText();

        System.out.println(SuccessMessage);


        System.out.println("End testing testCase01 ");
    }

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        options.addArguments("--user-data-dir=C:\\Users\\pravi\\AppData\\Local\\Google\\Chrome\\User Data");
        options.addArguments("--profile-directory=Profile 3");
        options.addArguments("--disable-extensions");
        options.addArguments("--disable-popup-blocking");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    // @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}