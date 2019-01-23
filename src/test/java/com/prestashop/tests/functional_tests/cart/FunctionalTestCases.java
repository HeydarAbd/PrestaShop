package com.prestashop.tests.functional_tests.cart;

import com.prestashop.pages.CheckoutDeleteTest;
import com.prestashop.pages.HomeLogout;
import com.prestashop.pages.HomePage;
import com.prestashop.pages.IconDelete;
import com.prestashop.utilities.Config;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FunctionalTestCases extends TestBase {

    HomePage lgn = new HomePage();
    HomeLogout hlo = new HomeLogout();
    IconDelete iD = new IconDelete();
    CheckoutDeleteTest cdt = new CheckoutDeleteTest();



    @Test(priority = 1)
    public void cartLoginTest(){
        Driver.getDriver().get(Config.getProperty("url"));
        lgn.chooseTheProduct();
        lgn.verifyCartContainsTheProduct();
        Assert.assertEquals(HomePage.productNameBeforeClick, HomePage.productNameBeforeClick);
        lgn.signIn(Config.getProperty("userName"), Config.getProperty("passWord"));
        lgn.verifyCartContainsTheProduct();
        Assert.assertEquals(HomePage.productNameBeforeClick, HomePage.productNameBeforeClick);
    }


    @Test(priority = 2)
    public void cartLogoutTest(){
        Driver.getDriver().get(Config.getProperty("url"));
        lgn.signIn(Config.getProperty("userName"), Config.getProperty("passWord"));
        hlo.goToMainPage();
        timeWait2();
        lgn.chooseTheProduct();
        lgn.verifyCartContainsTheProduct();
        Assert.assertEquals(HomePage.productNameBeforeClick, HomePage.productNameBeforeClick);
        //8.Log out
        timeWait2();
        lgn.logOut();
        //9.Verify word empty is displayed in the Cart icon
        lgn.verifyWordEmpty();
        Assert.assertTrue(lgn.verifyWordEmpty().contains(Config.getProperty("emptyword")));
    }


    @Test(priority = 3)
    public void cartIconDeleteTest(){
        Driver.getDriver().get(Config.getProperty("url"));
        iD.chooseTheProductBeforeContinue();
        iD.continueShopping();
        iD.hoverOverCartIcon();
        timeWait2();
        Assert.assertTrue(lgn.verifyWordEmpty().contains(Config.getProperty("emptyword")));

    }

    @Test(priority = 4)
    public void cartCheckoutDeleteTest(){
        Driver.getDriver().get(Config.getProperty("url"));
        iD.chooseTheProductBeforeContinue();
        iD.continueShopping();
        cdt.chooseTheProduct2();
        timeWait2();
        cdt.proceedCheckOut();
        //7.Verify message Your shopping cart contains: 2 Products
        Assert.assertEquals(cdt.verifyMessage(), Config.getProperty("verifyMessage1"));
        //8.Click the delete icon to delete one of the products
        cdt.clickTheDeleteIcon();
        //9.Verify message Your shopping cart contains: 1Product
        timeWait2();
        Assert.assertEquals(cdt.verifyMessage(), Config.getProperty("verifyMessage2"));
        //10.Click the delete icon to delete the second product
        cdt.clickTheDeleteIcon();
        timeWait2();
        Assert.assertEquals(cdt.verifyEmptyMessage(), Config.getProperty("verifyMessage3"));

    }




}
