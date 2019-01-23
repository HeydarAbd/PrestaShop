package com.prestashop.utilities;

import com.github.javafaker.Faker;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class TestBase {

    protected static WebDriver driver;
    Faker fakeGuy;
    protected static Actions actions  = new Actions(Driver.getDriver());



    @BeforeClass
    public void setUpClass(){
       driver =  Driver.getDriver();
    }

    @BeforeMethod
    public void beforeMethod() throws InterruptedException {

        fakeGuy = new Faker();
        actions = new Actions(Driver.getDriver());
        Thread.sleep(1000);
    }


    @AfterMethod
    public void tearDown() throws InterruptedException {
       Driver.closeDriver();
    }


    public static void timeWait2(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }











}
