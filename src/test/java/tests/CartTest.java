package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

import java.util.List;

public class CartTest extends BaseTest {

    @Test
    public void emptyCartTest(){
        LoginPage lgnPage = new LoginPage(driver);
        InventoryPage invPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        JsonNode data = DataDriven.jsonReader();
        SoftAssert soft = new SoftAssert();

        String username = data.get("validLogin").get("username").asText();
        String password = data.get("validLogin").get("password").asText();
        lgnPage.validLogin(username, password);
        soft.assertTrue(invPage.cartIconDisplayed());

        cartPage.cartClick();
        soft.assertTrue(cartPage.yourCartDisplayed());

        soft.assertTrue(cartPage.isEmptyCart());

        soft.assertAll();
    }

    @Test
    public void addSpecificProductsTest(){
        LoginPage lgnPage = new LoginPage(driver);
        InventoryPage invPage = new InventoryPage(driver);
        CartPage cartPage = new CartPage(driver);
        JsonNode data = DataDriven.jsonReader();
        SoftAssert soft = new SoftAssert();


        String username = data.get("validLogin").get("username").asText();
        String password = data.get("validLogin").get("password").asText();
        lgnPage.validLogin(username, password);
        soft.assertTrue(invPage.cartIconDisplayed());

        invPage.addSpecificProducts();
        soft.assertTrue(driver.findElement(By.cssSelector("a[aria-label=\"Cart, 3 items\"]")).isDisplayed());

        cartPage.cartClick();
        soft.assertTrue(cartPage.yourCartDisplayed());

        soft.assertTrue(cartPage.addSpecificProducts());

        soft.assertFalse(cartPage.isEmptyCart());

        soft.assertAll();
    }
}
