package com.prestashop.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver(){

    }

    public  static WebDriver driver;

    public static WebDriver getDriver(){
        if(driver ==null){
            switch (Config.getProperty("browser")){
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "ie":
                    if (BrowserUtils.ISMAC)
                        throw new WebDriverException("You are operating Mac OS which doesn't support Internet Explorer");
                    WebDriverManager.iedriver().setup();
                    driver = new InternetExplorerDriver();
                    break;
                case "safari":
                    if (BrowserUtils.ISWINDOWS)
                        throw new WebDriverException("You are operating Windows OS which doesn't support Safari");
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;
            }

        }
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(12, TimeUnit.SECONDS);
        return driver;
    }





    public static void closeDriver(){
        if (driver!=null){
            driver.close();
            driver = null;
        }
    }






}
