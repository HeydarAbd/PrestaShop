package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutDeleteTest extends TestBase {

    public CheckoutDeleteTest(){
        PageFactory.initElements(Driver.getDriver(), this);
    }



    @FindBy(xpath = "(//a[@class='product-name'])[1]")
    static WebElement hoverOverTheProduct2;

    @FindBy(xpath = "(//a[@class='button ajax_add_to_cart_button btn btn-default'])[1]/span")
    static WebElement addToCartBtn;

    @FindBy(xpath = "//a[@class='btn btn-default button button-medium']/span")
    static WebElement proceedCheckoutBtn;

    @FindBy(xpath = "//span[@class='heading-counter']")
    static WebElement messageSpot;

    @FindBy (xpath = "(//i[@class='icon-trash'])[1]")
    static WebElement deleteIconBtn;

    @FindBy (xpath = "//p[@class='alert alert-warning']")
    static WebElement cartIsEmptyMessage;


    public static void chooseTheProduct2(){
        HomePage.hoverOver(hoverOverTheProduct2);
        addToCartBtn.click();
    }

    public static void proceedCheckOut(){
        proceedCheckoutBtn.click();
    }

    public static String verifyMessage(){
        return messageSpot.getText();
    }


    public static void clickTheDeleteIcon(){
        deleteIconBtn.click();
    }

    public static String verifyEmptyMessage(){
        return cartIsEmptyMessage.getText();
    }


}
