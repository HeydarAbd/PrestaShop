package com.prestashop.pages;

import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IconDelete extends TestBase {


    public IconDelete(){
       PageFactory.initElements(driver = Driver.getDriver(), this);
    }


    @FindBy(xpath = "(//a[@class='product-name'])[3]")
    static WebElement hoverOverTheProduct;

    @FindBy(xpath = "(//a[@class='button ajax_add_to_cart_button btn btn-default'])[3]/span")
    static WebElement addToCartBtn;

    @FindBy (xpath = "//span[@class='continue btn btn-default button exclusive-medium']/span")
    static WebElement continueShoppingBtn;

    @FindBy(xpath = "//div[@class='shopping_cart']/a")
    static WebElement cartBtn;

    @FindBy(xpath = "//a[@class='ajax_cart_block_remove_link']")
    static WebElement xBtnOnCartIcon;



    public static void chooseTheProductBeforeContinue() {
        timeWait2();
        actions.sendKeys(Keys.PAGE_DOWN);
        HomePage.hoverOver(hoverOverTheProduct);
        addToCartBtn.click();
}

    public static void continueShopping(){
        continueShoppingBtn.click();
    }

    public static void hoverOverCartIcon(){
        actions.sendKeys(Keys.PAGE_UP);
        timeWait2();
        actions.moveToElement(cartBtn).perform();
        timeWait2();
        xBtnOnCartIcon.click();
    }






}
