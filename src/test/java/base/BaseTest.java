package base;

import factory.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;

public class BaseTest {
    protected WebDriver driver;
   // protected String baseUrl = "https://the-internet.herokuapp.com";
    protected String baseUrl;

    @BeforeMethod
    public void setUp(){
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();  old hard coded setup for driver

        driver= DriverFactory.createDriver();
        driver.manage().window().maximize();
        baseUrl = ConfigReader.get("baseUrl");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        if (driver!=null){
            driver.quit();
        }
    }

    public WebDriver getDriver() {
        return driver;
    }
}
