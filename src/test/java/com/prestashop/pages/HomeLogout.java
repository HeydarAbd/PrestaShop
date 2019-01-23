package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomeLogout extends TestBase {


    public HomeLogout(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(className = "icon-home")
    static WebElement homePageBtn;

    @FindBy(className = "logout")
    static  WebElement logoutBtn;






    public static void goToMainPage(){
        homePageBtn.click();
        timeWait2();
    }


    public static void logOutMethod(){
        logoutBtn.click();
    }







}
