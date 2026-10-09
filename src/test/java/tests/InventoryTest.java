package tests;

import base.BaseTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.InventoryPage;
import pages.LoginPage;
import utils.DataDriven;

public class InventoryTest extends BaseTest {

    @Test
    public void inventoryTest(){
        InventoryPage invPage = new InventoryPage(driver);
        LoginPage lgnPage = new LoginPage(driver);
        JsonNode data = DataDriven.jsonReader();
        SoftAssert soft = new SoftAssert();

        String username = data.get("validLogin").get("username").asText();
        String password = data.get("validLogin").get("password").asText();
        lgnPage.validLogin(username, password);

        soft.assertTrue(invPage.pageTitle());
        soft.assertTrue(invPage.cartIcon());
        soft.assertEquals(invPage.productsNum(),6);
        soft.assertAll();
    }
}
