package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtils {

    public static void takeScreenshot(WebDriver driver, String testName){
        try{
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);

            File destination = new File("screenshots/"+testName+"_"+System.currentTimeMillis()+".png");

            Files.createDirectories(destination.getParentFile().toPath());
            Files.copy(source.toPath(),destination.toPath());

        }
        catch (IOException e){
            System.out.println("Failed to capture screenshot"+e.getMessage());
        }


    }


}
