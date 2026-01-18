package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.ConfigReader;

public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(){
        driver.set(new ChromeDriver());
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){
        if(driver.get()!=null){
            driver.get().quit();
            driver.remove();
        }
    }



//    public static WebDriver createDriver(){
//
//        String browser = ConfigReader.get("browser");
//
//        if(browser == null){
//            browser = "chrome";
//        }
//
//        switch (browser.toLowerCase()){
//            case "firefox":
//                return new FirefoxDriver();
//            case "chrome":
//            default:
//                return new ChromeDriver();
//        }
//    }
}
