package com.techcenture.base;

import com.github.javafaker.Faker;
import com.techcenture.config.ConfigReader;
import com.techcenture.driver.Driver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {

    protected static WebDriver driver;
    protected Faker faker;

    @BeforeMethod
    public void beforeMethod(){
        //some configuration before the automation tests kick in
        faker = new Faker();
        driver = Driver.getDriver();
        driver.get(ConfigReader.getProperty("url"));
    }

    @AfterMethod
    public void afterMethod(){
//        Driver.quitDriver();
//        driver = null;
    }
}
