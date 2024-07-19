package com.techcenture.e2e;

import com.techcenture.base.BaseTest;
import com.techcenture.pages.AllOrdersPage;
import com.techcenture.pages.LoginPage;
import com.techcenture.pages.OrderPage;
import com.techcenture.utils.ExcelReader;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Map;

public class PlaceOrderTests extends BaseTest {

    @Test(dataProvider = "orders")
    public void placeOrderTest(Map<String,String> data){

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Tester", "test");

        AllOrdersPage allOrdersPage = new AllOrdersPage(driver);
        //navigateToOrderPage() is inherited from BasePage class.
        //there reason we are not calling this method from
        //BasePage is that we cannot create an object of BasePage,
        //because it is abstract
        allOrdersPage.navigateToOrderPage();

        OrderPage orderPage = new OrderPage(driver);

        orderPage.enterProductInformation(data);

        orderPage.clickCalculateBtn();
        orderPage.verifyCalculation();

        orderPage.enterAddressInformation(data);

        orderPage.enterPaymentInformation(data);

        orderPage.clickProcessBtn();
    }


    @DataProvider(name = "orders")
    public Object[][] getData() {
        ExcelReader reader = new ExcelReader("src/main/resources/test_data/Test_Data.xlsx", "orders");
        return reader.getData();
    }


}

