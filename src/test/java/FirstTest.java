import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FirstTest {

    @Test
    public void openGoogle() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        String title = driver.getTitle();
        System.out.println("Page title is: " + title);

        Assert.assertTrue(title.contains("Google"),
                "Title does not contain expected text");


        //System.out.println("Page title is: " + driver.getTitle());

        driver.quit();
    }
}
