import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class HerokuAppTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @Test
    public void verifyGoogleSearchResultsContainQuery()
    {
        //1. Locate search box
        WebElement searchBox = driver.findElement(By.name("q"));

        //2. Type search text
        String query = "Selenium WebDriver";
        searchBox.sendKeys(query);

        //3. Submit search
        searchBox.submit();

        // 4. Validate page title
        String title = driver.getTitle();
        System.out.println("Page title after search: " + title);
        Assert.assertTrue(title.contains(query));

//        Assert.assertTrue(title.contains("Selenium WebDriver"),
//                "Search did not work as expected");
    }

    @Test

    public void loginWithValidCredentials(){
        driver.get("https://the-internet.herokuapp.com/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")))
                .sendKeys("tomsmith");

        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        driver.findElement(By.cssSelector("button[type='submit']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

        String successMsg = driver.findElement(By.id("flash")).getText();

        Assert.assertTrue(successMsg.contains("You logged into a secure area"),"Login was not successful");

    }


    @Test

    public void verifyDynamicContentLoads(){

        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));

        driver.findElement(By.cssSelector("#start button")).click();

        // Wait for loading to finish and content to appear
        WebElement finishText = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("finish"))
        );

        Assert.assertTrue(finishText.getText().contains("Hello World!"),
                "Dynamic content did not load correctly");
    }

    @Test

    public void waitForButtonToEnable(){
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        WebElement enableButton = driver.findElement(By.xpath("//button[text()='Enable']"));
        enableButton.click();

        WebElement inputBox = driver.findElement(By.cssSelector("#input-example input"));
        wait.until(ExpectedConditions.elementToBeClickable(inputBox));
        inputBox.sendKeys("Now I can type");

        Assert.assertTrue(inputBox.isEnabled(), "Input box is not enabled");

    }

    @Test
    public void handleSimpleAlert(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
        Alert alert = driver.switchTo().alert();

        String alertText = alert.getText();
        System.out.println("Alert text: " + alertText);

        alert.accept();

        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("You successfully clicked an alert"));

    }

    @Test

    public void handleConfirmAlert(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("You clicked: Cancel"));
    }

    @Test

    public void handlePromptAlert(){
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Automation");
        alert.accept();

        String result = driver.findElement(By.id("result")).getText();
        Assert.assertTrue(result.contains("Automation"));
    }

    @Test

    public void browserNavigation(){
        driver.get("https://the-internet.herokuapp.com");
        driver.findElement(By.linkText("Form Authentication")).click();

        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        Assert.assertTrue(driver.getTitle().contains("The Internet"));
    }



    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
