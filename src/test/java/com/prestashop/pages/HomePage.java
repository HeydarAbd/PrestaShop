package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import sun.awt.windows.WEmbeddedFrame;

import java.util.List;

public class HomePage extends TestBase {

    //WebDriver driver;
    static Actions actions = new Actions(Driver.getDriver());

    public HomePage(){
       // this.driver = Driver.getDriver();
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy (xpath = "(//a[@class='product-name'])[3]")
    static WebElement hoverOverTheProduct;

    @FindBy(xpath = "(//a[@class='button ajax_add_to_cart_button btn btn-default'])[3]/span")
    static WebElement addToCartBtn;

    @FindBy(xpath = "//span[@class='cross']")
    static WebElement xBtn;

    @FindBy(xpath = "//div[@class='shopping_cart']/a")
    static WebElement cartBtn;

    @FindBy(xpath = "//a[@class='cart-images']/img")
    static WebElement productName;

    @FindBy(className = "login")
    static WebElement signInBtn;

    @FindBy (id = "email")
    static WebElement userName;

    @FindBy (id = "passwd")
    static WebElement password;

    @FindBy(className = "logout")
    static  WebElement logoutBtn;

    @FindBy(className = "ajax_cart_no_product")
    static WebElement emptyOnCartBtn;



    public static String productNameBeforeClick = "";
    public static String productNameAfterClick = "";
    public static String messageOnCart="";



    public static void chooseTheProduct() {
        timeWait2();
        actions.sendKeys(Keys.PAGE_DOWN);
        hoverOver(hoverOverTheProduct);

        productNameBeforeClick =  hoverOverTheProduct.getText();
        System.out.println(productNameBeforeClick);
        timeWait2();
        addToCartBtn.click();
        xBtn.click();
    }


    public static void verifyCartContainsTheProduct(){
        actions.sendKeys(Keys.PAGE_UP);
        hoverOver(cartBtn);
        timeWait2();
        productNameAfterClick = productName.getAttribute("alt");
    }


    public static void hoverOver(WebElement element){
        actions.moveToElement(element).perform();
    }


    public static void signIn(String usern, String passw){
        signInBtn.click();
        userName.sendKeys(usern);
        password.sendKeys(passw + Keys.ENTER);
    }


    public static void logOut(){
        logoutBtn.click();
    }

    public static String verifyWordEmpty(){
        actions.moveToElement(emptyOnCartBtn).perform();
        messageOnCart = emptyOnCartBtn.getText();
        return messageOnCart;
    }








}
