import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleSearchTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @Test
    public void searchInGoogle(){
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

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
