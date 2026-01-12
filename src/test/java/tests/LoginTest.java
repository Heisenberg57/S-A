package tests;

import base.BaseTest;
import listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.LoginPage;

@Listeners(TestListener.class)
public class LoginTest extends BaseTest {


    @Test
    public void loginWithValidCredentials(){
        LoginPage loginPage = new LoginPage(driver,baseUrl);

        loginPage.enterUsername("tomsmith");
        loginPage.enterpassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String message = loginPage.getFlashMessage();
//        Assert.assertTrue(message.contains("You logged into a secure area"),
//                "Login was not successful");
        Assert.assertTrue(false, "Forcing failure to test screenshot");



    }

}
