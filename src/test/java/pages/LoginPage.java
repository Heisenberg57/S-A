package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    //Locators - UI info here
    private By usernameInput = By.id("username");
    private By passwordInput = By.id("password");
    private By loginButton = By.cssSelector("button[type='submit']");
    private By flashMessage = By.id("flash");

    //constructor
    public LoginPage(WebDriver driver){
        this.driver=driver;
    }

    //Page Actions(business logic)
    public void enterUsername(String username){
        driver.findElement(usernameInput).sendKeys(username);
    }

    public void enterpassword(String password){
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickLogin(){
        driver.findElement(loginButton).click();
    }

    public String getFlashMessage(){
        return driver.findElement(flashMessage).getText();
    }
}
