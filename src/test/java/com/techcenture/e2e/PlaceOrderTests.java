package com.techcenture.e2e;

import com.techcenture.base.BaseTest;
import com.techcenture.pages.AllOrdersPage;
import com.techcenture.pages.LoginPage;
import com.techcenture.pages.OrderPage;
import org.testng.annotations.Test;

public class PlaceOrderTests extends BaseTest {

    @Test
    public void placeOrderTest(){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Tester", "test");

        AllOrdersPage allOrdersPage = new AllOrdersPage(driver);
        //navigateToOrderPage() is inherited from BasePage class.
        //there reason we are not calling this method from
        //BasePage is that we cannot create an object of BasePage,
        //because it is abstract
        allOrdersPage.navigateToOrderPage();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.enterProductInformation("ScreenSaver", 34);

        orderPage.clickCalculateBtn();
        orderPage.verifyCalculation();

        orderPage.enterAddressInformation("Kevin Lee", "123 main street", "Mclean", "VA", "22102");

        orderPage.enterPaymentInformation("Visa", "4358973459834", "12/27");

        orderPage.clickProcessBtn();
    }



}
