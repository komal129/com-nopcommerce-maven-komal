package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.pages.*;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

public class ComputerTest extends BaseTest {

    HomePage homePage = new HomePage();
    DeskTopPage deskTopPage = new DeskTopPage();
    BuildYourOwnComputerPage buildYourOwnComputerPage = new BuildYourOwnComputerPage();
    ShoppingCartPage shoppingCartPage = new ShoppingCartPage();
    CheckOutAsGuest checkOutAsGuestPage = new CheckOutAsGuest();
    BillingCheckOutPage checkOutPage = new BillingCheckOutPage();
    ShippingMethodCheckoutPage checkoutPage1 = new ShippingMethodCheckoutPage();
    PaymentMethodCheckOut checkOutPage2 = new PaymentMethodCheckOut();
    PaymentCheckOut checkOutPage3 = new PaymentCheckOut();
    ConfirmOrderPage confirmOrderPage = new ConfirmOrderPage();
    CompletedCheckoutPage completedCheckoutPage = new CompletedCheckoutPage();

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder(){
        homePage.selectMenu("computers");
        homePage.clickOnDeskTop();
        deskTopPage.selectSortByName("Name: Z to A");
        Map<String, ArrayList> mapArrays = deskTopPage.arrangeProductInDescendingOrder();
        Assert.assertEquals(mapArrays.get("originalProductsName"), mapArrays.get("afterSortByZToAProductsName"));
    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        homePage.selectMenu("computers");
        homePage.clickOnDeskTop();
        deskTopPage.selectSortByName("5");
        deskTopPage.clickOnAddToCart();
        Assert.assertEquals(buildYourOwnComputerPage.VerifytheTextBuildyourowncomputer(),"Build your own computer" , "Text is not Display");
        buildYourOwnComputerPage.selectProcessor("1");
        buildYourOwnComputerPage.selectRam("5");
        buildYourOwnComputerPage.selectHDDRadio();
        buildYourOwnComputerPage.selectOSRadiVistaPremium();
        buildYourOwnComputerPage.selectSoftware();
        Assert.assertEquals(buildYourOwnComputerPage.VerifythePrice(),"$1,475.00" , "Price is not match");
        buildYourOwnComputerPage.clickOnAddToCartButton();
        Assert.assertEquals(buildYourOwnComputerPage.VerifytheMessageOnTopGreenbar(),"The product has been added to your shopping cart","Message doesnot match");
        buildYourOwnComputerPage.clickOnCloseBar();
        buildYourOwnComputerPage.mouseHoverOnShoppingCart();
        buildYourOwnComputerPage.clickOnShoppingCart();
        Assert.assertEquals(shoppingCartPage.verifyShoppingCartText(),"Shopping cart","Text is not display");
        shoppingCartPage.updateQuantity();
        shoppingCartPage.UpdateShoppingCart();
        Assert.assertEquals(shoppingCartPage.VerifytheTotalPrice(),"$2,950.00","Price is not match");
        shoppingCartPage.clickOnCheckBox();
        shoppingCartPage.clickOnCheckOutButton();
        Assert.assertEquals(checkOutAsGuestPage.verifyWelComeText(),"Welcome, Please Sign In!","Text is not matched");
        checkOutAsGuestPage.clickOnAsGuestButton();
        //Fill ALl Fields
        checkOutPage.enterBillingFirstName("John");
        checkOutPage.enterBillingLastName("Smith");
        checkOutPage.enterBillingEmailAddress("john@example.com");
        checkOutPage.selectCountryFromDropDown("233");
        checkOutPage.selectStateFromDropDown("0");
        checkOutPage.enterBillingCity("London");
        checkOutPage.enterBillingAddress("4,Oxford Street");
        checkOutPage.enterBillingZipcode("W2 4TR");
        checkOutPage.enterBillingPhoneNumber("5665995116994");
        checkOutPage.clickOnCheckOutButton();
        Thread.sleep(5000);
        checkoutPage1.clickOnRadioButtonTextDayAir();
        checkoutPage1.clickOnContinue();
        checkOutPage2.clickOnCreditCard();
        checkOutPage2.clickOnContinue();
        checkOutPage3.enterCardHolderName("John Smith");
        checkOutPage3.enterCardNumber("5555555555554444");
        checkOutPage3.selectExpiryMonthFromDropDown("5");
        checkOutPage3.selectExpiryYearFromDropDown("2024");
        checkOutPage3.enterCardCode("3101");
        checkOutPage3.clickOnContinueButton();
        Assert.assertEquals(confirmOrderPage.VerifyCreditCardPaymentMethod(),"Credit Card","Method is not correct");
        Assert.assertEquals(confirmOrderPage.VerifyShippingMethod(),"Next Day Air","method is not correct");
        Assert.assertEquals(confirmOrderPage.VerifytheTotalPrice(),"$2,950.00","Price is not Match");
        confirmOrderPage.clickOnConFirm();
        Assert.assertEquals(completedCheckoutPage.verifyThankYouText(),"Thank you","Text is not displayed");
        Assert.assertEquals(completedCheckoutPage.verifySucessOrderText(),"Your order has been successfully processed!","Text isnot Display");
        completedCheckoutPage.clickOnContinue();
        Assert.assertEquals(homePage.verifyWelComeText(),"Welcome to our store","Text is not displayed");
    }

}
