package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LoginPage {
    WebDriver driver;
    WaitUtils wait;
    private static final String LOGIN_PATH = "/login";

    //Locators - UI info here
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By flashMessage = By.id("flash");

    //constructor
    public LoginPage(WebDriver driver,String baseUrl){
        this.driver=driver;
        driver.get(baseUrl+LOGIN_PATH);
        System.out.println("BASE URL = " + baseUrl);
        this.wait = new WaitUtils(driver);
    }

    //Page Actions(business logic)
    public void enterUsername(String username){

       // driver.findElement(usernameInput).sendKeys(username);
        wait.waitForVisibility(usernameInput).sendKeys(username);
    }

    public void enterpassword(String password){

        //driver.findElement(passwordInput).sendKeys(password);
        wait.waitForVisibility(passwordInput).sendKeys(password);
    }

    public void clickLogin(){

        //driver.findElement(loginButton).click();
        wait.waitForClickability(loginButton).click();
    }

    public String getFlashMessage(){
        //return driver.findElement(flashMessage).getText();
        return wait.waitForVisibility(flashMessage).getText();
    }
}
