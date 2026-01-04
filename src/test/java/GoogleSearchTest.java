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

public class GoogleSearchTest {

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

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
