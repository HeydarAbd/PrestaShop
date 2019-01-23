package com.prestashop.tests.smoke_tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AccountInformation {

    WebDriver driver;
    public static String userName="";

    @BeforeClass
    public void beforeClass(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }


    @BeforeMethod
    public void beforeMethod() throws InterruptedException {
        driver.get("http://automationpractice.com/index.php");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
    }


    @Test
    public void logInMyAccount() throws InterruptedException {
        driver.findElement(By.className("login")).click();
        driver.findElement(By.id("email")).sendKeys("heydar.abdullayev@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("cybertek2018");
        driver.findElement(By.id("SubmitLogin")).click();
        Thread.sleep(3000);
        //title contains My account
        String title = driver.getTitle();
        Assert.assertTrue(title.contains("My account"));
        // verify the actual user name equals to expected user name
        userName = driver.findElement(By.linkText("Christiano Ronaldo")).getText();
        Assert.assertTrue(userName.equals("Christiano Ronaldo"));
    }



    @Test(dependsOnMethods = "logInMyAccount")
    public void loginMyPersonalInformation() throws InterruptedException {


        driver.findElement(By.linkText("Sign out")).click();

        driver.findElement(By.linkText("Sign in")).click();


        Thread.sleep(3000);

//        driver.findElement(By.linkText("Sign out")).click();
//        Thread.sleep(3000);
//        driver.findElement(By.linkText("Sign in")).click();


        driver.findElement(By.id("email")).sendKeys("heydar.abdullayev@gmail.com");
        driver.findElement(By.id("passwd")).sendKeys("cybertek2018");
        driver.findElement(By.id("SubmitLogin")).click();
        Thread.sleep(3000);
        //click the perosonal information
        driver.findElement(By.xpath("//i[@class='icon-user']")).click();
        //driver.findElement(By.className("icon-user")).click();
        Thread.sleep(3000);

        //Verify title contains Identity
        Assert.assertTrue(driver.getTitle().contains("Identity"));
        //Verify that first name and last name matches the full name on top
        String firstName = driver.findElement(By.id("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.id("lastname")).getAttribute("value");

        Assert.assertEquals(userName, firstName + " " + lastName);
        //Click on Save button
        driver.findElement(By.name("submitIdentity")).click();
        //Verify error message “The password you entered is incorrect.”
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText().contains("The password you entered is incorrect."));
        //Click onBack to your account
        driver.findElement(By.xpath("//i[@class='icon-chevron-left']/../../../../li[1]")).click();
        //Verify that title contains My account
        Assert.assertTrue(driver.getTitle().contains("My account"));
    }




    @Test (dependsOnMethods = "loginMyPersonalInformation")
    public void loginMyAddresses() throws InterruptedException {
//        driver.findElement(By.xpath("//a[@class='login']")).click();
//        driver.findElement(By.id("email")).sendKeys("heydar.abdullayev@gmail.com");
//        driver.findElement(By.id("passwd")).sendKeys("cybertek2018");
//        driver.findElement(By.id("SubmitLogin")).click();
//        Thread.sleep(3000);
        //Click on My addresses
        driver.findElement(By.linkText("My addresses")).click();
        //Click on Add a new address
        driver.findElement(By.linkText("Add a new address")).click();
        Thread.sleep(3000);
        //Verify that first name and last name matches the full name on top
        userName = driver.findElement(By.linkText("Christiano Ronaldo")).getText();
        String firstName = driver.findElement(By.id("firstname")).getAttribute("value");
        String lastName = driver.findElement(By.id("lastname")).getAttribute("value");
        Assert.assertEquals(userName, firstName + " " + lastName);
        //Delete the first name
        driver.findElement(By.id("firstname")).clear();
        Thread.sleep(3000);
        //Click save
        driver.findElement(By.id("submitAddress")).click();
        //Verify error message “firstname is required.”
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='alert alert-danger']")).getText().contains("firstname is required."));


    }







}
