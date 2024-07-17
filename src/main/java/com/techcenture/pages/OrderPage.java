package com.techcenture.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class OrderPage extends BasePage{

    public OrderPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "ctl00_MainContent_fmwOrder_ddlProduct")
    protected WebElement productDropDown;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtQuantity")
    protected WebElement quantityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtUnitPrice")
    protected WebElement priceInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtDiscount")
    protected WebElement discountInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtTotal")
    protected WebElement totalInput;

    @FindBy(xpath = "//input[@value='Calculate']")
    protected WebElement calculateBtn;

    @FindBy(id = "ctl00_MainContent_fmwOrder_txtName")
    protected WebElement customerNameInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox2")
    protected WebElement streetAddressInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox3")
    protected WebElement cityInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox4")
    protected WebElement stateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox5")
    protected WebElement zipInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox6")
    protected WebElement cardNumberInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_TextBox1")
    protected WebElement exprDateInput;

    @FindBy(id = "ctl00_MainContent_fmwOrder_InsertButton")
    protected WebElement processBtn;

    @FindBy(tagName = "strong")
    protected  WebElement orderCreatedSuccessMsg;


    public void enterProductInformation(String productName, int quantity) {
        Select productSelect = new Select(productDropDown);
        productSelect.selectByValue(productName);
        quantityInput.sendKeys( String.valueOf(quantity));
    }

    /**
     * This method will calculate the price of the products based on quantity and discount
     */
    public void verifyCalculation(){
        String quantity = quantityInput.getAttribute("value"); //6
        String price = priceInput.getAttribute("value");
        String discount = discountInput.getAttribute("value");

        int quantityInt = Integer.parseInt(quantity);
        double priceDouble = Double.parseDouble(price);
        int discountInt = Integer.parseInt(discount);
        double total = quantityInt * priceDouble;
        double expectedDiscountedTotal = total - (total * (discountInt / 100.0));

        String totalValue = totalInput.getAttribute("value");
        double actualDiscountedTotal = Double.parseDouble(totalValue);

        Assert.assertEquals(actualDiscountedTotal, expectedDiscountedTotal);
    }

    public void clickCalculateBtn(){
        calculateBtn.click();
    }

    public void enterAddressInformation(String customerName, String street, String city, String state, String zip) {
        customerNameInput.sendKeys(customerName);
        streetAddressInput.sendKeys(street);
        cityInput.sendKeys(city);
        stateInput.sendKeys(state);
        zipInput.sendKeys(zip);
    }

    public void enterPaymentInformation(String cardName, String cardNumber, String expDate) {
        WebElement card = driver.findElement(By.xpath("//input[@value='" + cardName + "']"));
        card.click();
        cardNumberInput.sendKeys(cardName);
        exprDateInput.sendKeys(expDate);
    }

    public void clickProcessBtn(){
        processBtn.click();
        verifySuccessOrderMessage();
    }


    private void verifySuccessOrderMessage(){
        Assert.assertTrue(orderCreatedSuccessMsg.isDisplayed());
    }

}
