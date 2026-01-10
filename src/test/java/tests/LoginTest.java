package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest {
    private static final Logger log = LoggerFactory.getLogger(LoginTest.class);
    WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
        driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginWithValidCredentials(){
        loginPage.enterUsername("tomsmith");
        loginPage.enterpassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String message = loginPage.getFlashMessage();
        Assert.assertTrue(message.contains("You logged into a secure area"),
                "Login was not successful");


    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
