import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FirstTestRefactror {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
    }

    @Test
    public void verifyGoogleTitle() {
        String title = driver.getTitle();
        System.out.println("Page title is: " + title);

        Assert.assertTrue(title.contains("Google"),
                "Title does not contain expected text");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
