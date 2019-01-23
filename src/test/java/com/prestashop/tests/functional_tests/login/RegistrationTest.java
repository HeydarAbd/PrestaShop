package com.prestashop.tests.functional_tests.login;

import com.github.javafaker.Faker;
import com.prestashop.utilities.Driver;
import com.prestashop.utilities.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Random;

public class RegistrationTest extends TestBase {

    Random myRandom;


    Faker fakeGuy;
    String email;
    String firstName;
    String lastName;
    String password;

    int dayday;
    int monthmonth;
    int yearyear;

    String email2;
    Faker fakeGirl;


    Actions actions;




    @Test(priority = 1)
    public void registrationTest() throws InterruptedException {
        //click the sign in
        Actions act = new Actions(Driver.getDriver());
        WebElement signIn =  Driver.getDriver().findElement(By.linkText("Sign in"));
        act.moveToElement(signIn).click().perform();
        Thread.sleep(3000);
        fakeGuy  = new Faker();
        email = fakeGuy.internet().emailAddress();
        firstName = fakeGuy.name().firstName();
        lastName = fakeGuy.name().lastName();
        //Enter new validemail to the email field
        Driver.getDriver().findElement(By.id("email_create")).sendKeys(email);
        Thread.sleep(3000);
        //Click on Create Account
        Driver.getDriver().findElement(By.id("SubmitCreate")).click();
        //Verify that that email link displays currentemail
        Thread.sleep(2000);
        String emailAfterReg = Driver.getDriver().findElement(By.id("email")).getAttribute("value");
        System.out.println(emailAfterReg);
        Assert.assertTrue(emailAfterReg.equals(email));
        //Fill out all the required steps
        Driver.getDriver().findElement(By.id("customer_firstname")).sendKeys(firstName);
        Driver.getDriver().findElement(By.id("customer_lastname")).sendKeys(lastName);
        password = fakeGuy.internet().password();
        Driver.getDriver().findElement(By.id("passwd")).sendKeys(password);
        Thread.sleep(2000);

        myRandom = new Random();

        dayday = myRandom.nextInt(30) + 1;
        WebElement dayoff = Driver.getDriver().findElement(By.id("days"));
        Select day = new Select(dayoff);
        day.selectByIndex(dayday);
        System.out.println(dayday);

        //======================================================
        monthmonth = myRandom.nextInt(12) + 1;
        WebElement monthoff = Driver.getDriver().findElement(By.id("months"));
        Select month = new Select(monthoff);
        month.selectByIndex(monthmonth);
        System.out.println(monthmonth);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++
        yearyear = myRandom.nextInt(118) + 1;
        WebElement yearoff = Driver.getDriver().findElement(By.id("years"));
        Select year = new Select(yearoff);
        year.selectByIndex(yearyear);
        System.out.println(yearyear);


        Driver.getDriver().findElement(By.id("newsletter")).click();
        Driver.getDriver().findElement(By.id("optin")).click();
        Driver.getDriver().findElement(By.id("company")).sendKeys(fakeGuy.company().name());
        Driver.getDriver().findElement(By.id("address1")).sendKeys(fakeGuy.address().streetName());
        Driver.getDriver().findElement(By.id("address2")).sendKeys(fakeGuy.address().streetName());
        Driver.getDriver().findElement(By.id("city")).sendKeys(fakeGuy.address().city());
        int stateNum = myRandom.nextInt(50) + 1;
        WebElement states = Driver.getDriver().findElement(By.id("id_state"));
        Select state = new Select(states);
        Thread.sleep(2000);
        state.selectByIndex(stateNum);


        Driver.getDriver().findElement(By.id("postcode")).sendKeys(fakeGuy.address().zipCode().substring(0,5));
        Driver.getDriver().findElement(By.id("other")).sendKeys(fakeGuy.lordOfTheRings().character());
        Driver.getDriver().findElement(By.id("phone")).sendKeys(fakeGuy.phoneNumber().cellPhone());
        Driver.getDriver().findElement(By.id("phone_mobile")).sendKeys(fakeGuy.phoneNumber().cellPhone());
        Driver.getDriver().findElement(By.id("alias")).sendKeys(fakeGuy.lordOfTheRings().location());
        //Click on Register
        Driver.getDriver().findElement(By.id("submitAccount")).click();

        //Verify that correct first and last name is displayed correctly on top right
        System.out.println(firstName + " " + lastName);
        String nameOnteRight = Driver.getDriver().findElement(By.xpath("//a[@class='account']/span")).getText();
        Assert.assertEquals(nameOnteRight, firstName + " " + lastName);


        //Click on My personal information
        Driver.getDriver().findElement(By.xpath("//i[@class='icon-user']/../span")).click();


        //Verify that personal information is displayed correctly
        String firstNameAfterRegister = Driver.getDriver().findElement(By.id("firstname")).getAttribute("Value");
        Assert.assertEquals(firstNameAfterRegister, firstName );
        String lastNameAfterRegister = Driver.getDriver().findElement(By.id("lastname")).getAttribute("value");
        Assert.assertEquals(lastNameAfterRegister, lastName);
        Thread.sleep(3000);
        //Click on Back to your account
        Driver.getDriver().findElement(By.xpath("(//a[@class='btn btn-default button button-small'])[2]")).click();
        //Click on My addresses verify that address information is displayed correctly
        Thread.sleep(2000);
        Driver.getDriver().findElement(By.xpath("//i[@class='icon-building']/../span")).click();
        //Click on sign outlink
        Driver.getDriver().findElement(By.className("logout")).click();
        Thread.sleep(3000);
        //Login using the email and password if the current user
        Driver.getDriver().findElement(By.id("email")).sendKeys(email);
        Driver.getDriver().findElement(By.id("passwd")).sendKeys(password);
        Thread.sleep(2000);
        Driver.getDriver().findElement(By.id("SubmitLogin")).click();
        //Verify that correct first and last name is displayed correctly on top right
        String nameOntheTop = Driver.getDriver().findElement(By.xpath("//a[@class='account']/span")).getText();
        Assert.assertEquals(nameOntheTop, firstName + " " + lastName);
    }


    @Test (priority = 2)
    public void errorMessageValidationFirstName() throws InterruptedException {
        Thread.sleep(3000);
        //3.Click Sign in link
        Driver.getDriver().findElement(By.linkText("Sign in")).click();
        Thread.sleep(2000);
        //4.Enter new valide mail to the email field
        fakeGirl = new Faker();
        email2 = fakeGirl.internet().emailAddress();
        Driver.getDriver().findElement(By.id("email_create")).sendKeys(email2);
        //5.Click on Create Account
        Driver.getDriver().findElement(By.id("SubmitCreate")).click();
        //6.Fill all the required steps except for first name
        Thread.sleep(3000);
        Driver.getDriver().findElement(By.id("customer_lastname")).sendKeys(fakeGirl.name().lastName());
        Driver.getDriver().findElement(By.id("passwd")).sendKeys(fakeGirl.internet().password());
        Driver.getDriver().findElement(By.id("address1")).sendKeys(fakeGirl.address().streetName());
        Driver.getDriver().findElement(By.id("city")).sendKeys(fakeGirl.address().city());
        myRandom = new Random();
        int stateNums =myRandom.nextInt(50) + 1;
        WebElement states = Driver.getDriver().findElement(By.id("id_state"));
        Select state2 = new Select(states);
        Thread.sleep(2000);
        state2.selectByIndex(stateNums);
        Driver.getDriver().findElement(By.id("postcode")).sendKeys(fakeGirl.address().zipCode().substring(0,5));
        Driver.getDriver().findElement(By.id("phone_mobile")).sendKeys(fakeGirl.phoneNumber().cellPhone());
        //7.Click on Register
        Thread.sleep(2000);
        Driver.getDriver().findElement(By.id("submitAccount")).click();
        //8.Verify that error messagefirstnameis required.is displayed
        WebElement errorMessage = Driver.getDriver().findElement(By.xpath("//div[@class='alert alert-danger']/ol/li"));
        String ermessage = errorMessage.getAttribute("textContent").trim();
        Assert.assertEquals(ermessage, "firstname is required.");
    }


    @Test (priority = 3)
    public void cartDetails() throws InterruptedException {

        //3.Click on any productthat is not onsale
        myRandom = new Random();
        int chooseProduct = myRandom.nextInt(4) +1;
        Driver.getDriver().findElement(By.xpath("(//div[@class='product-container'])["+chooseProduct+"]")).click();
        Thread.sleep(5000);
        //4.Enter a random quantity between 2and 5
        myRandom = new Random();
        int quantt = myRandom.nextInt(4)+2;
        Driver.getDriver().findElement(By.id("quantity_wanted")).clear();
        String quanttt = String.valueOf(quantt);
        System.out.println(quantt);
        Driver.getDriver().findElement(By.id("quantity_wanted")).sendKeys(quanttt);
        //5.Select a different size
        myRandom = new Random();
        int siz = myRandom.nextInt(2)+1;
        WebElement sizes = Driver.getDriver().findElement(By.id("group_1"));
        Select size = new Select(sizes);
        size.selectByIndex(siz);
        //6.Click on add to cart
        Thread.sleep(2000);
        Driver.getDriver().findElement(By.id("add_to_cart")).click();
        //7.Verify confirmation message Product successfully added to your shopping cart
        Thread.sleep(3000);
        String confMes = Driver.getDriver().findElement(By.xpath("//i[@class='icon-ok']/..")).getText();
        Assert.assertEquals(confMes, "Product successfully added to your shopping cart");
        //8.Dismiss the confirmation window by clicking on the x icon
        Thread.sleep(2000);
        Driver.getDriver().findElement(By.className("cross")).click();
        //9.Click on the company logo
        Thread.sleep(2000);
        Driver.getDriver().findElement(By.xpath("//div[@id='header_logo']/a")).click();
        //10.Click on any product that is onsale
        Driver.getDriver().findElement(By.xpath("(//div[@class='product-container'])[5]")).click();
        //11.Enter a random quantity between 2and 5
        Driver.getDriver().findElement(By.id("quantity_wanted")).clear();
        String quanttt2 = String.valueOf(quantt);
        System.out.println(quantt);
        Driver.getDriver().findElement(By.id("quantity_wanted")).sendKeys(quanttt2);
        //12.Select a different size
        WebElement sizes2 = Driver.getDriver().findElement(By.id("group_1"));
        Select size2 = new Select(sizes2);
        size2.selectByIndex(siz);
        //13.Click on add to cart
        Thread.sleep(2000);
        Driver.getDriver().findElement(By.id("add_to_cart")).click();
        //14.Verifyconfirmation message Product successfully added to your shopping cart
        Thread.sleep(3000);
        String confMes2 = Driver.getDriver().findElement(By.xpath("//i[@class='icon-ok']/..")).getText();
        Assert.assertEquals(confMes2, "Product successfully added to your shopping cart");
        //15.Dismiss the confirmation window by clicking on the x icon
        Thread.sleep(2000);
        Driver.getDriver().findElement(By.className("cross")).click();
        //16.Hover over the Cart icon
        Thread.sleep(2000);
        actions = new Actions(Driver.getDriver());
        WebElement cart = Driver.getDriver().findElement(By.xpath("//div[@class='shopping_cart']/a"));
        actions.moveToElement(cart).perform();
        Thread.sleep(3000);
        //17.Verify that correct total is displayed







    }





}
