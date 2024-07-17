package com.techcenture.e2e;

import com.techcenture.base.BaseTest;
import static com.techcenture.config.ConfigReader.*;
import com.techcenture.pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

    @Test
    public void webOrdersLoginPositiveTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(getProperty("username"), getProperty("password"));
    }

    @Test
    public void webOrdersLoginNegativeTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("Incorrect", "WrongPass!");
        loginPage.verifyLoginError();
    }

}
