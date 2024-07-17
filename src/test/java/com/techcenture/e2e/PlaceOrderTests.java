package com.techcenture.e2e;

import com.github.javafaker.Faker;
import com.techcenture.base.BaseTest;
import com.techcenture.pages.AllOrdersPage;
import com.techcenture.pages.LoginPage;
import com.techcenture.pages.OrderPage;
import com.techcenture.utils.CommonUtils;
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

        String[] products = {"MyMoney", "FamilyAlbum", "ScreenSaver"};
        Integer randomIndex = faker.random().nextInt(0, 2);

        String randomProduct = products[randomIndex];
        int randomQuantity = faker.random().nextInt(1, 50);

        orderPage.enterProductInformation(randomProduct, randomQuantity);

        orderPage.clickCalculateBtn();
        orderPage.verifyCalculation();

        String fullName = faker.name().fullName();
        String streetAddress = faker.address().streetAddress();
        String cityName = faker.address().cityName();
        String state = faker.address().stateAbbr();
        String zip = faker.address().zipCode().substring(0, 5);

        orderPage.enterAddressInformation(fullName, streetAddress, cityName, state, zip);

        String[] cards = {"Visa", "MasterCard", "American Express"};
        randomIndex = faker.random().nextInt(0, 2);

        String randomCard = cards[randomIndex];

        String cardNumber = CommonUtils.randomDigits(15);

        String expDate = CommonUtils.generateRandomExpirationDate();

        orderPage.enterPaymentInformation(randomCard, cardNumber, expDate);

        orderPage.clickProcessBtn();
    }

}

