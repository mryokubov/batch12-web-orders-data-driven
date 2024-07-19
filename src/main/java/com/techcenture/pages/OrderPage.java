package com.techcenture.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Map;

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


    public void enterProductInformation(Map<String,String> data) {
        Select productSelect = new Select(productDropDown);
        productSelect.selectByValue(data.get("product_name"));
        quantityInput.sendKeys( String.valueOf(data.get("quantity")));
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

    public void enterAddressInformation(Map<String,String> data) {
        customerNameInput.sendKeys(data.get("customer_name"));
        streetAddressInput.sendKeys(data.get("street"));
        cityInput.sendKeys(data.get("city"));
        stateInput.sendKeys(data.get("state"));
        zipInput.sendKeys(data.get("zip"));
    }

    public void enterPaymentInformation(Map<String,String> data) {
        WebElement card = driver.findElement(By.xpath("//input[@value='" + data.get("card") + "']"));
        card.click();
        cardNumberInput.sendKeys(data.get("card_number"));
        exprDateInput.sendKeys(data.get("expr_date"));
    }

    public void clickProcessBtn(){
        processBtn.click();
        verifySuccessOrderMessage();
    }


    private void verifySuccessOrderMessage(){
        Assert.assertTrue(orderCreatedSuccessMsg.getText().trim().equals("New order has been successfully added."));
    }

}
